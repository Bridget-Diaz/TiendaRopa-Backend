package pe.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.entity.DetallePedido;
import pe.edu.cibertec.entity.Pedido;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    List<DetallePedido> findByPedido_IdPedido(Integer idPedido);
    
    
    // acabo de agregar otra  mas eleiminar si da error xd
    List<DetallePedido> findByPedido(Pedido pedido);
}