package pe.edu.cibertec.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.dto.admin.AdminProductoRequestDTO;
import pe.edu.cibertec.dto.admin.AdminProductoResponseDTO;
import pe.edu.cibertec.entity.Categoria;
import pe.edu.cibertec.entity.Producto;
import pe.edu.cibertec.repository.CategoriaRepository;
import pe.edu.cibertec.repository.ProductoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminProductoService {

    private final ProductoRepository productoRepo;
    private final CategoriaRepository categoriaRepo;

    public AdminProductoService(ProductoRepository productoRepo,
                                 CategoriaRepository categoriaRepo) {
        this.productoRepo = productoRepo;
        this.categoriaRepo = categoriaRepo;
    }

    public List<AdminProductoResponseDTO> listarTodos() {
        return productoRepo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AdminProductoResponseDTO obtenerPorId(Integer id) {
        Producto p = productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
        return toDTO(p);
    }

    public AdminProductoResponseDTO crear(AdminProductoRequestDTO dto) {
        Categoria categoria = categoriaRepo.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + dto.getIdCategoria()));

        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setPrecioDescuento(dto.getPrecioDescuento());
        p.setStock(dto.getStock());
        p.setCategoria(categoria);
        p.setActivo(dto.getActivo() != null ? dto.getActivo() : true);
        p.setDestacado(dto.getDestacado() != null ? dto.getDestacado() : false);
        p.setNuevo(dto.getNuevo() != null ? dto.getNuevo() : false);

        return toDTO(productoRepo.save(p));
    }

    public AdminProductoResponseDTO actualizar(Integer id, AdminProductoRequestDTO dto) {
        Producto p = productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));

        if (dto.getIdCategoria() != null) {
            Categoria cat = categoriaRepo.findById(dto.getIdCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            p.setCategoria(cat);
        }
        if (dto.getNombre() != null)         p.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null)    p.setDescripcion(dto.getDescripcion());
        if (dto.getPrecio() != null)         p.setPrecio(dto.getPrecio());
        if (dto.getPrecioDescuento() != null) p.setPrecioDescuento(dto.getPrecioDescuento());
        if (dto.getStock() != null)          p.setStock(dto.getStock());
        if (dto.getActivo() != null)         p.setActivo(dto.getActivo());
        if (dto.getDestacado() != null)      p.setDestacado(dto.getDestacado());
        if (dto.getNuevo() != null)          p.setNuevo(dto.getNuevo());

        return toDTO(productoRepo.save(p));
    }

    public void eliminar(Integer id) {
        Producto p = productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
        // Soft delete
        p.setActivo(false);
        productoRepo.save(p);
    }

    public boolean toggleActivo(Integer id) {
        Producto p = productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
        p.setActivo(!p.getActivo());
        productoRepo.save(p);
        return p.getActivo();
    }

    private AdminProductoResponseDTO toDTO(Producto p) {
        AdminProductoResponseDTO dto = new AdminProductoResponseDTO();
        dto.setIdProducto(p.getIdProducto());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setPrecioDescuento(p.getPrecioDescuento());
        dto.setStock(p.getStock());
        dto.setIdCategoria(p.getCategoria().getIdCategoria());
        dto.setNombreCategoria(p.getCategoria().getNombreCategoria());
        dto.setActivo(p.getActivo());
        dto.setDestacado(p.getDestacado());
        dto.setNuevo(p.getNuevo());
        dto.setFechaCreacion(p.getFechaCreacion());
        return dto;
    }
}
