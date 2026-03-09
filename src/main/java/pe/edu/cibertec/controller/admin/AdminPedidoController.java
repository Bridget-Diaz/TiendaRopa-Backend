package pe.edu.cibertec.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.dto.admin.AdminPedidoResponseDTO;
import pe.edu.cibertec.service.admin.AdminPedidoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/pedidos")
@PreAuthorize("hasRole('administrador')")
public class AdminPedidoController {

    private final AdminPedidoService pedidoService;

    public AdminPedidoController(AdminPedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // GET /api/admin/pedidos → listar todos los pedidos
    @GetMapping
    public ResponseEntity<List<AdminPedidoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    // GET /api/admin/pedidos/{id} → detalle completo de un pedido
    @GetMapping("/{id}")
    public ResponseEntity<AdminPedidoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoService.obtenerPorId(id));
    }

    // PATCH /api/admin/pedidos/{id}/estado → actualizar estado del pedido
    // Body: { "estado": "EN_PREPARACION" }
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Map<String, String>> actualizarEstado(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        pedidoService.actualizarEstado(id, nuevoEstado);
        return ResponseEntity.ok(Map.of(
            "mensaje", "Estado actualizado a: " + nuevoEstado
        ));
    }
}