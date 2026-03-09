package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.edu.cibertec.entity.CarritoItem;
import java.util.List;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
    List<CarritoItem> findByCarrito_IdCarrito(Integer idCarrito);
    @Query("""
    		SELECT DISTINCT ci
    		FROM CarritoItem ci
    		LEFT JOIN FETCH ci.componentes c
    		LEFT JOIN FETCH c.packComponente
    		LEFT JOIN FETCH c.talla
    		LEFT JOIN FETCH ci.producto
    		LEFT JOIN FETCH ci.talla
    		WHERE ci.carrito.idCarrito = :idCarrito
    		""")
    		List<CarritoItem> findItemsCompletosByCarrito(@Param("idCarrito") Integer idCarrito);
}