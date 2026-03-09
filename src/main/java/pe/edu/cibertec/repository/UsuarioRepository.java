	package pe.edu.cibertec.repository;
	
	import java.util.Optional;
	
	import org.springframework.data.jpa.repository.JpaRepository;
	
	import pe.edu.cibertec.entity.*;
	
	public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	    Optional<Usuario> findByEmail(String email);
	}
