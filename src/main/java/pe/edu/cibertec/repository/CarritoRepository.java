package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.cibertec.entity.Carrito;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByUsuario_IdUsuario(Integer idUsuario);
    
    @EntityGraph(attributePaths = {
    	    "items",
    	    "items.producto",
    	    "items.talla",
    	    // "items.componentes",
    	    "items.componentes.packComponente",
    	    "items.componentes.talla"
    	})
    	@Query("select c from Carrito c where c.usuario.email = :email")
    	Carrito findCarritoCompleto(@Param("email") String email);
    
}