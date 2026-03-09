package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.entity.CarritoItemComponente;
import java.util.List;

public interface CarritoItemComponenteRepository extends JpaRepository<CarritoItemComponente, Integer> {
    List<CarritoItemComponente> findByCarritoItem_IdCarritoItem(Integer idCarritoItem);
}