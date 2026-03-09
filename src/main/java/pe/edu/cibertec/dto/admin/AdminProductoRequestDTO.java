package pe.edu.cibertec.dto.admin;

import java.math.BigDecimal;

public class AdminProductoRequestDTO {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal precioDescuento;
    private Integer stock;
    private Integer idCategoria;
    private Boolean activo;
    private Boolean destacado;
    private Boolean nuevo;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public BigDecimal getPrecioDescuento() { return precioDescuento; }
    public void setPrecioDescuento(BigDecimal precioDescuento) { this.precioDescuento = precioDescuento; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public Boolean getDestacado() { return destacado; }
    public void setDestacado(Boolean destacado) { this.destacado = destacado; }
    public Boolean getNuevo() { return nuevo; }
    public void setNuevo(Boolean nuevo) { this.nuevo = nuevo; }
}
