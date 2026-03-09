package pe.edu.cibertec.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.dto.admin.AdminPedidoResponseDTO;
import pe.edu.cibertec.entity.Pedido;
import pe.edu.cibertec.entity.PedidoSeguimiento;
import pe.edu.cibertec.repository.DetallePedidoRepository;
import pe.edu.cibertec.repository.PedidoRepository;
import pe.edu.cibertec.repository.PedidoSeguimientoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminPedidoService {

    private final PedidoRepository pedidoRepo;
    private final DetallePedidoRepository detalleRepo;
    private final PedidoSeguimientoRepository seguimientoRepo;

    public AdminPedidoService(PedidoRepository pedidoRepo,
                               DetallePedidoRepository detalleRepo,
                               PedidoSeguimientoRepository seguimientoRepo) {
        this.pedidoRepo = pedidoRepo;
        this.detalleRepo = detalleRepo;
        this.seguimientoRepo = seguimientoRepo;
    }

    public List<AdminPedidoResponseDTO> listarTodos() {
        return pedidoRepo.findAllByOrderByFechaDesc()//aca daba error solo lo volvi a escribir y... ya no dio error xdddd
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AdminPedidoResponseDTO obtenerPorId(Integer id) {
        Pedido p = pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
        return toDTO(p);
    }

    public void actualizarEstado(Integer id, String nuevoEstado) {
        // Validar que el estado sea un valor permitido
        List<String> estadosValidos = List.of(
                "PENDIENTE", "EN_PREPARACION", "ENVIADO", "ENTREGADO", "CANCELADO"
        );
        if (!estadosValidos.contains(nuevoEstado)) {
            throw new RuntimeException("Estado inválido: " + nuevoEstado);
        }

        Pedido p = pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
        p.setEstado(nuevoEstado);
        pedidoRepo.save(p);

        // Registrar en seguimiento
        PedidoSeguimiento seg = new PedidoSeguimiento();
        seg.setPedido(p);
        seg.setEstado(nuevoEstado);
        seg.setFecha(LocalDateTime.now());
        seg.setComentario("Estado actualizado por administrador");
        seguimientoRepo.save(seg);
    }

    private AdminPedidoResponseDTO toDTO(Pedido p) {
        AdminPedidoResponseDTO dto = new AdminPedidoResponseDTO();
        dto.setIdPedido(p.getIdPedido());
        dto.setNombreCliente(p.getUsuario().getNombre() + " " + p.getUsuario().getApellido());
        dto.setEmailCliente(p.getUsuario().getEmail());
        dto.setFecha(p.getFecha());
        dto.setTotal(p.getTotal());
        dto.setEstado(p.getEstado());
        dto.setCiudad(p.getDireccion().getCiudad());
        dto.setTipoEntrega(p.getDireccion().getTipoEntrega());

        // Items del pedido
        var items = detalleRepo.findByPedido(p)
                .stream()
                .map(d -> new AdminPedidoResponseDTO.ItemDTO(
                        d.getProducto().getNombre(),
                        d.getTalla() != null ? d.getTalla().getNombreTalla() : "—",
                        d.getCantidad(),
                        d.getPrecioUnitario(),
                        d.getSubtotal()
                ))
                .collect(Collectors.toList());
        dto.setItems(items);

        return dto;
    }
}