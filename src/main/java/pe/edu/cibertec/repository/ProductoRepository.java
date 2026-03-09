package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.entity.Producto;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Productos activos
   // List<Producto> findByActivoTrue();
    
	@EntityGraph(attributePaths = {
		    "categoria",
		    "tallas",
		    "tallas.talla"
		    //"componentes"
		})
		List<Producto> findByActivoTrue();
	
    // Productos destacados
    List<Producto> findByDestacadoTrueAndActivoTrue();
    
    // Productos nuevos
    List<Producto> findByNuevoTrueAndActivoTrue();
    
    // Productos por categoría
    List<Producto> findByCategoriaIdCategoriaAndActivoTrue(Integer idCategoria);
    
    // Buscar productos por nombre (contiene)
    List<Producto> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);
    
    // Productos por rango de precio
    List<Producto> findByPrecioBetweenAndActivoTrue(BigDecimal precioMin, BigDecimal precioMax);
    
    // Productos con descuento
    @Query("SELECT p FROM Producto p WHERE p.precioDescuento IS NOT NULL AND p.activo = true")
    List<Producto> findProductosConDescuento();
    
    // Productos más recientes
    @Query("SELECT p FROM Producto p WHERE p.activo = true ORDER BY p.fechaCreacion DESC")
    List<Producto> findProductosRecientes();
    
    // Buscar con filtros múltiples
    @Query("SELECT p FROM Producto p WHERE " +
           "(:categoria IS NULL OR p.categoria.idCategoria = :categoria) AND " +
           "(:precioMin IS NULL OR p.precio >= :precioMin) AND " +
           "(:precioMax IS NULL OR p.precio <= :precioMax) AND " +
           "(:destacado IS NULL OR p.destacado = :destacado) AND " +
           "p.activo = true")
    List<Producto> buscarConFiltros(
        @Param("categoria") Integer categoria,
        @Param("precioMin") BigDecimal precioMin,
        @Param("precioMax") BigDecimal precioMax,
        @Param("destacado") Boolean destacado
    );
    
    @EntityGraph(attributePaths = {
    	    "categoria",
    	    "tallas",
    	    "tallas.talla",
    	    "componentes",
    	    "componentes.tallas",
    	    "componentes.tallas.talla"
    	})
    	@Query("select p from Producto p where p.idProducto = :id")
    	Producto findProductoCompleto(@Param("id") Integer id);
    
    
}
