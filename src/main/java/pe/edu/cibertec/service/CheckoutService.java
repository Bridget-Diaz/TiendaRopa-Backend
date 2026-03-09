package pe.edu.cibertec.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.dto.CheckoutRequestDTO;
import pe.edu.cibertec.dto.CheckoutResponseDTO;
import pe.edu.cibertec.entity.*;
import pe.edu.cibertec.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckoutService {

    private final UsuarioRepository usuarioRepository;
    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;
    private final CarritoItemComponenteRepository carritoItemComponenteRepository;
    private final DireccionEnvioRepository direccionEnvioRepository;
    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final PagoRepository pagoRepository;

    public CheckoutService(UsuarioRepository usuarioRepository,
                           CarritoRepository carritoRepository,
                           CarritoItemRepository carritoItemRepository,
                           CarritoItemComponenteRepository carritoItemComponenteRepository,
                           DireccionEnvioRepository direccionEnvioRepository,
                           PedidoRepository pedidoRepository,
                           DetallePedidoRepository detallePedidoRepository,
                           PagoRepository pagoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.carritoRepository = carritoRepository;
        this.carritoItemRepository = carritoItemRepository;
        this.carritoItemComponenteRepository = carritoItemComponenteRepository;
        this.direccionEnvioRepository = direccionEnvioRepository;
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.pagoRepository = pagoRepository;
    }

    @Transactional
    public CheckoutResponseDTO procesarCheckout(String email, CheckoutRequestDTO dto) {

        // 1. Obtener usuario
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Obtener carrito
        Carrito carrito = carritoRepository.findByUsuario_IdUsuario(usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Carrito vacío"));

        List<CarritoItem> items = carritoItemRepository
                .findByCarrito_IdCarrito(carrito.getIdCarrito());

        if (items.isEmpty()) {
            throw new RuntimeException("No hay productos en el carrito");
        }

        // 3. Calcular total
        java.math.BigDecimal total = items.stream()
                .map(CarritoItem::getSubtotal)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

        // 4. Guardar dirección
        DireccionEnvio direccion = new DireccionEnvio();
        direccion.setUsuario(usuario);
        direccion.setTipoEntrega("DOMICILIO");
        direccion.setPais("Perú");
        direccion.setNombre(dto.getNombre());
        direccion.setApellido(dto.getApellido());
        direccion.setDni(dto.getDni());
        direccion.setDireccion(dto.getDireccion());
        direccion.setReferencia(dto.getReferencia());
        direccion.setCiudad(dto.getCiudad());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setTelefono(dto.getTelefono());
        direccion.setFechaCreacion(LocalDateTime.now());
        direccionEnvioRepository.save(direccion);

        // 5. Crear pedido
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setDireccion(direccion);
        pedido.setFecha(LocalDateTime.now());
        pedido.setTotal(total);
        pedido.setEstado("PENDIENTE");
        pedidoRepository.save(pedido);

        // 6. Guardar detalle del pedido
        for (CarritoItem item : items) {
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(item.getProducto());
            detalle.setTalla(item.getTalla());
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(item.getPrecioUnitario());
            detalle.setSubtotal(item.getSubtotal());
            detallePedidoRepository.save(detalle);
        }

        // 7. Registrar pago
        Pago pago = new Pago();
        pago.setPedido(pedido);
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setFechaPago(LocalDateTime.now());
        pago.setMonto(total);
        pago.setEstado("APROBADO");
        pagoRepository.save(pago);

        // 8. Vaciar carrito
        carritoItemRepository.deleteAll(items);

        return new CheckoutResponseDTO(
                pedido.getIdPedido(),
                pedido.getEstado(),
                total,
                dto.getMetodoPago(),
                "¡Pedido realizado con éxito!"
        );
    }
}