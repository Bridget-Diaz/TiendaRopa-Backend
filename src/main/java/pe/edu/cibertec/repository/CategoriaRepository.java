package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Optional<Categoria> findByNombreCategoria(String nombreCategoria);
    
    List<Categoria> findByActivoTrue();
    
    List<Categoria> findByActivoOrderByNombreCategoriaAsc(Boolean activo);
}
