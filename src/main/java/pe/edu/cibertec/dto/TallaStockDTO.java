package pe.edu.cibertec.dto;

public class TallaStockDTO {
    
    private Integer idTalla;
    private String nombreTalla;
    private Integer stock;
    private Boolean disponible;
    
    // Constructor vacío
    public TallaStockDTO() {
    }
    
    // Constructor completo
    public TallaStockDTO(Integer idTalla, String nombreTalla, Integer stock) {
        this.idTalla = idTalla;
        this.nombreTalla = nombreTalla;
        this.stock = stock;
        this.disponible = stock > 0;
    }

    // Getters y Setters
    public Integer getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Integer idTalla) {
        this.idTalla = idTalla;
    }

    public String getNombreTalla() {
        return nombreTalla;
    }

    public void setNombreTalla(String nombreTalla) {
        this.nombreTalla = nombreTalla;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
        this.disponible = stock > 0;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
