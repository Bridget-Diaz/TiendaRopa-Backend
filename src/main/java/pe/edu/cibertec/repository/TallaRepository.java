package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.entity.Talla;

import java.util.List;
import java.util.Optional;

public interface TallaRepository extends JpaRepository<Talla, Integer> {

    Optional<Talla> findByNombreTalla(String nombreTalla);
    
    List<Talla> findAllByOrderByOrdenAsc();
}
