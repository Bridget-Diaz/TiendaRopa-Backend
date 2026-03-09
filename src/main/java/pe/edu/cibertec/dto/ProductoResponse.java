package pe.edu.cibertec.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductoResponse {
    
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal precioDescuento;
    private Integer stock;
    private String categoria;
    private Integer idCategoria;
    private Boolean destacado;
    private Boolean nuevo;
    private List<TallaStockDTO> tallasDisponibles;
    
    // NUEVO: Para identificar si es un pack
    private Boolean esPack;
    
    // NUEVO: Componentes del pack (si es un pack)
    private List<ComponenteDTO> componentes;
    
    // Constructor vacío
    public ProductoResponse() {
    }
    
    // Constructor completo
    public ProductoResponse(Integer idProducto, String nombre, String descripcion, 
                           BigDecimal precio, BigDecimal precioDescuento, Integer stock,
                           String categoria, Integer idCategoria,
                           Boolean destacado, Boolean nuevo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.precioDescuento = precioDescuento;
        this.stock = stock;
        this.categoria = categoria;
        this.idCategoria = idCategoria;
        this.destacado = destacado;
        this.nuevo = nuevo;
    }

    // Getters y Setters
    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPrecioDescuento() {
        return precioDescuento;
    }

    public void setPrecioDescuento(BigDecimal precioDescuento) {
        this.precioDescuento = precioDescuento;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Boolean getDestacado() {
        return destacado;
    }

    public void setDestacado(Boolean destacado) {
        this.destacado = destacado;
    }

    public Boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        this.nuevo = nuevo;
    }

    public Boolean getEsPack() {
        return esPack;
    }
    
    public void setEsPack(Boolean esPack) {
        this.esPack = esPack;
    }
    
    
    public List<TallaStockDTO> getTallasDisponibles() {
        return tallasDisponibles;
    }

    public void setTallasDisponibles(List<TallaStockDTO> tallasDisponibles) {
        this.tallasDisponibles = tallasDisponibles;
    }
    
    public List<ComponenteDTO> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<ComponenteDTO> componentes) {
        this.componentes = componentes;
    }
    
}