package pe.edu.cibertec.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.dto.admin.AdminCategoriaRequestDTO;
import pe.edu.cibertec.dto.admin.AdminCategoriaResponseDTO;
import pe.edu.cibertec.service.admin.AdminCategoriaService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/categorias")
@PreAuthorize("hasRole('administrador')")
public class AdminCategoriaController {

    private final AdminCategoriaService categoriaService;

    public AdminCategoriaController(AdminCategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // GET /api/admin/categorias → listar todas
    @GetMapping
    public ResponseEntity<List<AdminCategoriaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    // GET /api/admin/categorias/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AdminCategoriaResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    // POST /api/admin/categorias → crear
    @PostMapping
    public ResponseEntity<AdminCategoriaResponseDTO> crear(@RequestBody AdminCategoriaRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.crear(dto));
    }

    // PUT /api/admin/categorias/{id} → actualizar
    @PutMapping("/{id}")
    public ResponseEntity<AdminCategoriaResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody AdminCategoriaRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.actualizar(id, dto));
    }

    // DELETE /api/admin/categorias/{id} → soft delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Categoría eliminada correctamente"));
    }

    // PATCH /api/admin/categorias/{id}/toggle-activo
    @PatchMapping("/{id}/toggle-activo")
    public ResponseEntity<Map<String, Object>> toggleActivo(@PathVariable Integer id) {
        boolean nuevoEstado = categoriaService.toggleActivo(id);
        return ResponseEntity.ok(Map.of(
            "id", id,
            "activo", nuevoEstado,
            "mensaje", nuevoEstado ? "Categoría activada" : "Categoría desactivada"
        ));
    }
}