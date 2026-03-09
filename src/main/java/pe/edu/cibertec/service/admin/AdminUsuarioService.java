package pe.edu.cibertec.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.dto.admin.AdminUsuarioResponseDTO;
import pe.edu.cibertec.entity.Rol;
import pe.edu.cibertec.entity.Usuario;
import pe.edu.cibertec.repository.RolRepository;
import pe.edu.cibertec.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminUsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final RolRepository rolRepo;

    public AdminUsuarioService(UsuarioRepository usuarioRepo, RolRepository rolRepo) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
    }

    public List<AdminUsuarioResponseDTO> listarTodos() {
        return usuarioRepo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AdminUsuarioResponseDTO obtenerPorId(Integer id) {
        return toDTO(usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id)));
    }

    public boolean toggleBloqueo(Integer id) {
        Usuario u = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
        u.setBloqueado(!Boolean.TRUE.equals(u.getBloqueado()));
        usuarioRepo.save(u);
        return u.getBloqueado();
    }

    public void cambiarRol(Integer idUsuario, Integer idRol) {
        Usuario u = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + idUsuario));
        Rol rol = rolRepo.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + idRol));
        u.setRol(rol);
        usuarioRepo.save(u);
    }

    private AdminUsuarioResponseDTO toDTO(Usuario u) {
        AdminUsuarioResponseDTO dto = new AdminUsuarioResponseDTO();
        dto.setIdUsuario(u.getIdUsuario());
        dto.setNombre(u.getNombre());
        dto.setApellido(u.getApellido());
        dto.setEmail(u.getEmail());
        dto.setBloqueado(u.getBloqueado());
        dto.setRol(u.getRol().getNombreRol());
        dto.setIdRol(u.getRol().getIdRol());
        dto.setFechaRegistro(u.getFechaRegistro());
        return dto;
    }
}