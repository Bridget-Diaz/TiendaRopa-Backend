package pe.edu.cibertec.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.dto.admin.AdminUsuarioResponseDTO;
import pe.edu.cibertec.service.admin.AdminUsuarioService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/usuarios")
@PreAuthorize("hasRole('administrador')")
public class AdminUsuarioController {

    private final AdminUsuarioService usuarioService;

    public AdminUsuarioController(AdminUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET /api/admin/usuarios → listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<AdminUsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    // GET /api/admin/usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AdminUsuarioResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    // PATCH /api/admin/usuarios/{id}/bloquear → bloquear/desbloquear usuario
    @PatchMapping("/{id}/bloquear")
    public ResponseEntity<Map<String, Object>> toggleBloqueo(@PathVariable Integer id) {
        boolean bloqueado = usuarioService.toggleBloqueo(id);
        return ResponseEntity.ok(Map.of(
            "id", id,
            "bloqueado", bloqueado,
            "mensaje", bloqueado ? "Usuario bloqueado" : "Usuario desbloqueado"
        ));
    }

    // PATCH /api/admin/usuarios/{id}/rol → cambiar rol del usuario
    @PatchMapping("/{id}/rol")
    public ResponseEntity<Map<String, String>> cambiarRol(
            @PathVariable Integer id,
            @RequestBody Map<String, Integer> body) {
        usuarioService.cambiarRol(id, body.get("idRol"));
        return ResponseEntity.ok(Map.of("mensaje", "Rol actualizado correctamente"));
    }
}