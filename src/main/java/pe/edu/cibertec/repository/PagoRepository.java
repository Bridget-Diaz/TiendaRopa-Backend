package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.entity.Pago;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
    Optional<Pago> findByPedido_IdPedido(Integer idPedido);
}