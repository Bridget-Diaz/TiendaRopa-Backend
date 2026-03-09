package pe.edu.cibertec.service;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.dto.CategoriaResponse;
import pe.edu.cibertec.entity.Categoria;
import pe.edu.cibertec.repository.CategoriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponse> obtenerTodasLasCategorias() {
        return categoriaRepository.findByActivoTrue()
                .stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    public CategoriaResponse obtenerCategoriaPorId(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return convertirAResponse(categoria);
    }

    // Método helper para convertir Categoria a CategoriaResponse
    private CategoriaResponse convertirAResponse(Categoria categoria) {
        CategoriaResponse response = new CategoriaResponse();
        response.setIdCategoria(categoria.getIdCategoria());
        response.setNombreCategoria(categoria.getNombreCategoria());
        response.setDescripcion(categoria.getDescripcion());
        
        // Contar productos activos en esta categoría
        if (categoria.getProductos() != null) {
            long totalProductos = categoria.getProductos()
                    .stream()
                    .filter(p -> p.getActivo())
                    .count();
            response.setTotalProductos((int) totalProductos);
        }
        
        return response;
    }
}
