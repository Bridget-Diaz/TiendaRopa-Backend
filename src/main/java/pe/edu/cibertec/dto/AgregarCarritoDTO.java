package pe.edu.cibertec.dto;

import java.util.List;

public class AgregarCarritoDTO {

   // private Integer idUsuario; No se manda es un erro grave oscar :v ya que JWT lo manda automatico 
    private Integer idProducto;
    private Integer idTalla;        // solo para productos simples
    private Integer cantidad;
    private boolean esPack;
    private List<ComponenteSeleccionDTO> componentes; // solo para packs

    public AgregarCarritoDTO() {}

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public Integer getIdTalla() { return idTalla; }
    public void setIdTalla(Integer idTalla) { this.idTalla = idTalla; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public boolean isEsPack() { return esPack; }
    public void setEsPack(boolean esPack) { this.esPack = esPack; }

    public List<ComponenteSeleccionDTO> getComponentes() { return componentes; }
    public void setComponentes(List<ComponenteSeleccionDTO> componentes) { this.componentes = componentes; }
}