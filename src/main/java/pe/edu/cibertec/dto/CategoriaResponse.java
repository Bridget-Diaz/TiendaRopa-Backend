package pe.edu.cibertec.dto;

public class CategoriaResponse {
    
    private Integer idCategoria;
    private String nombreCategoria;
    private String descripcion;
    private Integer totalProductos;
    
    // Constructor vacío
    public CategoriaResponse() {
    }
    
    // Constructor
    public CategoriaResponse(Integer idCategoria, String nombreCategoria, 
                            String descripcion) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(Integer totalProductos) {
        this.totalProductos = totalProductos;
    }
}
