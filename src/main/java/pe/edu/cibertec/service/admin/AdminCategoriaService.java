package pe.edu.cibertec.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.dto.admin.AdminCategoriaRequestDTO;
import pe.edu.cibertec.dto.admin.AdminCategoriaResponseDTO;
import pe.edu.cibertec.entity.Categoria;
import pe.edu.cibertec.repository.CategoriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminCategoriaService {

    private final CategoriaRepository categoriaRepo;

    public AdminCategoriaService(CategoriaRepository categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    public List<AdminCategoriaResponseDTO> listarTodas() {
        return categoriaRepo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AdminCategoriaResponseDTO obtenerPorId(Integer id) {
        return toDTO(categoriaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id)));
    }

    public AdminCategoriaResponseDTO crear(AdminCategoriaRequestDTO dto) {
        Categoria c = new Categoria();
        c.setNombreCategoria(dto.getNombreCategoria());
        c.setDescripcion(dto.getDescripcion());
        c.setActivo(dto.getActivo() != null ? dto.getActivo() : true);
        return toDTO(categoriaRepo.save(c));
    }

    public AdminCategoriaResponseDTO actualizar(Integer id, AdminCategoriaRequestDTO dto) {
        Categoria c = categoriaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id));
        if (dto.getNombreCategoria() != null) c.setNombreCategoria(dto.getNombreCategoria());
        if (dto.getDescripcion() != null)     c.setDescripcion(dto.getDescripcion());
        if (dto.getActivo() != null)          c.setActivo(dto.getActivo());
        return toDTO(categoriaRepo.save(c));
    }

    public void eliminar(Integer id) {
        Categoria c = categoriaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id));
        // Soft delete
        c.setActivo(false);
        categoriaRepo.save(c);
    }

    public boolean toggleActivo(Integer id) {
        Categoria c = categoriaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id));
        c.setActivo(!c.getActivo());
        categoriaRepo.save(c);
        return c.getActivo();
    }

    private AdminCategoriaResponseDTO toDTO(Categoria c) {
        AdminCategoriaResponseDTO dto = new AdminCategoriaResponseDTO();
        dto.setIdCategoria(c.getIdCategoria());
        dto.setNombreCategoria(c.getNombreCategoria());
        dto.setDescripcion(c.getDescripcion());
        dto.setActivo(c.getActivo());
        dto.setFechaCreacion(c.getFechaCreacion());
        dto.setTotalProductos(c.getProductos() != null ? c.getProductos().size() : 0);
        return dto;
    }
}