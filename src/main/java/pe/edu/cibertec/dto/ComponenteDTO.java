package pe.edu.cibertec.dto;

import java.util.List;

public class ComponenteDTO {
	private Integer idComponente;
    private String nombreComponente;
    private List<TallaStockDTO> tallasDisponibles;

    // Constructores
    public ComponenteDTO() {
    }

    public ComponenteDTO(Integer idComponente, String nombreComponente, List<TallaStockDTO> tallasDisponibles) {
        this.idComponente = idComponente;
        this.nombreComponente = nombreComponente;
        this.tallasDisponibles = tallasDisponibles;
    }

    // Getters y Setters
    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public void setNombreComponente(String nombreComponente) {
        this.nombreComponente = nombreComponente;
    }

    public List<TallaStockDTO> getTallasDisponibles() {
        return tallasDisponibles;
    }

    public void setTallasDisponibles(List<TallaStockDTO> tallasDisponibles) {
        this.tallasDisponibles = tallasDisponibles;
    }
}
