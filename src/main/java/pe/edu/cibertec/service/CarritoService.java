package pe.edu.cibertec.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.dto.AgregarCarritoDTO;
import pe.edu.cibertec.dto.CarritoItemResponseDTO;
import pe.edu.cibertec.dto.ComponenteSeleccionDTO;
import pe.edu.cibertec.dto.ProductoResponse;
import pe.edu.cibertec.entity.*;
import pe.edu.cibertec.repository.*;
import pe.edu.cibertec.feign.ProductoFeignClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;
    private final CarritoItemComponenteRepository carritoItemComponenteRepository;
    private final UsuarioRepository usuarioRepository;

    private final ProductoFeignClient productoFeignClient;

    public CarritoService(
            CarritoRepository carritoRepository,
            CarritoItemRepository carritoItemRepository,
            CarritoItemComponenteRepository carritoItemComponenteRepository,
            UsuarioRepository usuarioRepository,
            ProductoFeignClient productoFeignClient) {

        this.carritoRepository = carritoRepository;
        this.carritoItemRepository = carritoItemRepository;
        this.carritoItemComponenteRepository = carritoItemComponenteRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoFeignClient = productoFeignClient;
    }

    // AGREGAR PRODUCTO
    @Transactional
    public void agregarProducto(AgregarCarritoDTO dto, String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = carritoRepository
                .findByUsuario_IdUsuario(usuario.getIdUsuario())
                .orElseGet(() -> crearCarrito(usuario));

        // 🔥 Producto desde Feign
        ProductoResponse productoFeign =
                productoFeignClient.obtenerProducto(dto.getIdProducto());

        if (productoFeign == null) {
            throw new RuntimeException("Producto no encontrado");
        }

        if (productoFeign.getStock() < dto.getCantidad()) {
            throw new RuntimeException(
                    "Stock insuficiente. Disponible: "
                            + productoFeign.getStock());
        }

        BigDecimal precio = productoFeign.getPrecioDescuento() != null
                ? productoFeign.getPrecioDescuento()
                : productoFeign.getPrecio();

        BigDecimal subtotal =
                precio.multiply(BigDecimal.valueOf(dto.getCantidad()));

        // Solo guardamos referencia ID producto
        Producto producto = new Producto();
        producto.setIdProducto(dto.getIdProducto());

        CarritoItem item = new CarritoItem();

        item.setCarrito(carrito);
        item.setProducto(producto);
        item.setCantidad(dto.getCantidad());
        item.setPrecioUnitario(precio);
        item.setSubtotal(subtotal);

        carritoItemRepository.save(item);
    }


    // OBTENER ITEMS
    public List<CarritoItemResponseDTO> obtenerItems(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = carritoRepository
                .findByUsuario_IdUsuario(usuario.getIdUsuario())
                .orElseGet(() -> crearCarrito(usuario));

        return carritoItemRepository
                .findItemsCompletosByCarrito(carrito.getIdCarrito())
                .stream()
                .map(this::mapearItem)
                .toList();
    }


    private CarritoItemResponseDTO mapearItem(CarritoItem item) {

        return new CarritoItemResponseDTO(
                item.getIdCarritoItem(),
                item.getProducto().getNombre(),
                item.getTalla() != null
                        ? item.getTalla().getNombreTalla()
                        : null,
                item.getCantidad(),
                item.getPrecioUnitario(),
                item.getSubtotal());
    }


    // ACTUALIZAR
    @Transactional
    public void actualizarCantidad(
            Integer idItem,
            Integer nuevaCantidad,
            String email){

        if (nuevaCantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor a 0");
        }

        CarritoItem item = carritoItemRepository.findById(idItem)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        if (!item.getCarrito().getUsuario().getEmail().equals(email)) {
            throw new RuntimeException("No autorizado");
        }

        item.setCantidad(nuevaCantidad);

        item.setSubtotal(
                item.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(nuevaCantidad)));

        carritoItemRepository.save(item);
    }


    // ELIMINAR
    @Transactional
    public void eliminarItem(Integer idItem, String email) {

        CarritoItem item = carritoItemRepository.findById(idItem)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        if (!item.getCarrito().getUsuario().getEmail().equals(email)) {
            throw new RuntimeException("No autorizado");
        }

        carritoItemRepository.delete(item);
    }


    // CREAR CARRITO
    private Carrito crearCarrito(Usuario usuario) {

        Carrito carrito = new Carrito();

        carrito.setUsuario(usuario);
        carrito.setFechaCreacion(LocalDateTime.now());

        return carritoRepository.save(carrito);
    }
}