package pe.edu.cibertec.dto;

/**
 * Representa la talla elegida para un componente del pack.
 * Ejemplo: { idPackComponente: 3, idTalla: 2 } → "Hoodie talla M"
 */
public class ComponenteSeleccionDTO {

    private Integer idPackComponente;  // ID del componente (Hoodie, Polo 1, Polo 2...)
    private Integer idTalla;           // talla elegida por el usuario para ese componente

    public ComponenteSeleccionDTO() {}

    public Integer getIdPackComponente() { return idPackComponente; }
    public void setIdPackComponente(Integer idPackComponente) { this.idPackComponente = idPackComponente; }

    public Integer getIdTalla() { return idTalla; }
    public void setIdTalla(Integer idTalla) { this.idTalla = idTalla; }
}