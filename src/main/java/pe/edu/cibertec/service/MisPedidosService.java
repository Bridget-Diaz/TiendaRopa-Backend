package pe.edu.cibertec.service;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.dto.PedidoResponseDTO;
import pe.edu.cibertec.entity.Pago;
import pe.edu.cibertec.entity.Pedido;
import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.repository.PagoRepository;
import pe.edu.cibertec.repository.PedidoRepository;
import pe.edu.cibertec.repository.UsuarioRepository;

import java.util.List;

@Service
public class MisPedidosService {

    private final PedidoRepository pedidoRepository;
    private final PagoRepository pagoRepository;
    private final UsuarioRepository usuarioRepository;

    public MisPedidosService(PedidoRepository pedidoRepository,
                              PagoRepository pagoRepository,
                              UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pagoRepository = pagoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<PedidoResponseDTO> obtenerMisPedidos(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Pedido> pedidos = pedidoRepository
                .findByUsuario_IdUsuarioOrderByFechaDesc(usuario.getIdUsuario());

        return pedidos.stream().map(pedido -> {
            String metodoPago = pagoRepository
                    .findByPedido_IdPedido(pedido.getIdPedido())
                    .map(Pago::getMetodoPago)
                    .orElse("N/A");

            return new PedidoResponseDTO(
                    pedido.getIdPedido(),
                    pedido.getFecha(),
                    pedido.getTotal(),
                    pedido.getEstado(),
                    metodoPago
            );
        }).toList();
    }
}