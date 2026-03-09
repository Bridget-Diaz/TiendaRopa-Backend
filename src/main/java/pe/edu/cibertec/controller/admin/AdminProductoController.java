package pe.edu.cibertec.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.dto.admin.AdminProductoRequestDTO;
import pe.edu.cibertec.dto.admin.AdminProductoResponseDTO;
import pe.edu.cibertec.service.admin.AdminProductoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/productos")
@PreAuthorize("hasRole('administrador')")
public class AdminProductoController {

    private final AdminProductoService productoService;

    public AdminProductoController(AdminProductoService productoService) {
        this.productoService = productoService;
    }

    // GET /api/admin/productos → listar todos (con paginación opcional)
    @GetMapping
    public ResponseEntity<List<AdminProductoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    // GET /api/admin/productos/{id} → detalle de un producto
    @GetMapping("/{id}")
    public ResponseEntity<AdminProductoResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    // POST /api/admin/productos → crear nuevo producto
    @PostMapping
    public ResponseEntity<AdminProductoResponseDTO> crear(@RequestBody AdminProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.crear(dto));
    }

    // PUT /api/admin/productos/{id} → actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<AdminProductoResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody AdminProductoRequestDTO dto) {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    // DELETE /api/admin/productos/{id} → eliminar producto (soft delete: activo=false)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Producto eliminado correctamente"));
    }

    // PATCH /api/admin/productos/{id}/toggle-activo → activar/desactivar
    @PatchMapping("/{id}/toggle-activo")
    public ResponseEntity<Map<String, Object>> toggleActivo(@PathVariable Integer id) {
        boolean nuevoEstado = productoService.toggleActivo(id);
        return ResponseEntity.ok(Map.of(
            "id", id,
            "activo", nuevoEstado,
            "mensaje", nuevoEstado ? "Producto activado" : "Producto desactivado"
        ));
    }
}