package pe.edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.entity.PedidoSeguimiento;

public interface PedidoSeguimientoRepository extends JpaRepository<PedidoSeguimiento, Integer>{
	List<PedidoSeguimiento> findByPedidoIdPedidoOrderByFechaDesc(Integer idPedido);

}
