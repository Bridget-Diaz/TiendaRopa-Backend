package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.dto.admin.AdminPedidoResponseDTO;
import pe.edu.cibertec.entity.Pedido;

import java.util.Collection;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	List<Pedido> findByUsuario_IdUsuarioOrderByFechaDesc(Integer idUsuario);
	
	//se agrego esto :v
		List<Pedido> findAllByOrderByFechaDesc();

		
	    

}