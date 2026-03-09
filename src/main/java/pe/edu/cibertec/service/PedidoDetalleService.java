package pe.edu.cibertec.service;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.dto.PedidoDetalleResponseDTO;
import pe.edu.cibertec.entity.DetallePedido;
import pe.edu.cibertec.entity.Pago;
import pe.edu.cibertec.entity.Pedido;
import pe.edu.cibertec.repository.DetallePedidoRepository;
import pe.edu.cibertec.repository.PagoRepository;
import pe.edu.cibertec.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoDetalleService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final PagoRepository pagoRepository;

    public PedidoDetalleService(PedidoRepository pedidoRepository,
                                 DetallePedidoRepository detallePedidoRepository,
                                 PagoRepository pagoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.pagoRepository = pagoRepository;
    }

    public PedidoDetalleResponseDTO obtenerDetalle(Integer idPedido, String email) {

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (!pedido.getUsuario().getEmail().equals(email)) {
            throw new RuntimeException("No autorizado");
        }

        String metodoPago = pagoRepository
                .findByPedido_IdPedido(idPedido)
                .map(Pago::getMetodoPago)
                .orElse("N/A");

        List<DetallePedido> detalles =
                detallePedidoRepository.findByPedido_IdPedido(idPedido);

        List<PedidoDetalleResponseDTO.ItemDTO> items = detalles.stream()
                .map(d -> new PedidoDetalleResponseDTO.ItemDTO(
                        d.getProducto().getNombre(),
                        d.getTalla() != null ? d.getTalla().getNombreTalla() : null,
                        d.getCantidad(),
                        d.getPrecioUnitario(),
                        d.getSubtotal()
                )).toList();

        return new PedidoDetalleResponseDTO(
                pedido.getIdPedido(),
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.getEstado(),
                metodoPago,
                items
        );
    }
}