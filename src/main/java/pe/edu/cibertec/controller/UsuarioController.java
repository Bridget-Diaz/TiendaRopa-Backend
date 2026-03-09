package pe.edu.cibertec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.entity.Perfil;
import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario") // ruta base para usuario
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/perfil")
    public ResponseEntity<?> perfilUsuario(Authentication authentication) {
        // Obtener email desde el token JWT
        String email = authentication.getName();

        // Buscar usuario en la base de datos
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear un objeto Perfil para devolver solo la info segura
        Perfil perfil = new Perfil();
        perfil.setNombre(usuario.getNombre());
        perfil.setApellido(usuario.getApellido());
        perfil.setEmail(usuario.getEmail());
        perfil.setRol(usuario.getRol().getNombreRol());
        perfil.setFechaRegistro(usuario.getFechaRegistro());

        // Devolverlo como respuesta
        return ResponseEntity.ok(perfil);
    }

}
