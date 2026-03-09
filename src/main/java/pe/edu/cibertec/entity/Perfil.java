	package pe.edu.cibertec.entity;
	
	import java.time.LocalDateTime;
	
	public class Perfil {
		private String nombre;
	    private String apellido;
	    private String email;
	    private String rol;
	    private LocalDateTime fechaRegistro;
	
	    // getters y setters
	    public String getNombre() {
	        return nombre;
	    }
	
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
	
	    public String getApellido() {
	        return apellido;
	    }
	
	    public void setApellido(String apellido) {
	        this.apellido = apellido;
	    }
	
	    public String getEmail() {
	        return email;
	    }
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }
	
	    public String getRol() {
	        return rol;
	    }
	
	    public void setRol(String rol) {
	        this.rol = rol;
	    }
	
	    public LocalDateTime getFechaRegistro() {
	        return fechaRegistro;
	    }
	
	    public void setFechaRegistro(LocalDateTime fechaRegistro) {
	        this.fechaRegistro = fechaRegistro;
	    }
	
	}
