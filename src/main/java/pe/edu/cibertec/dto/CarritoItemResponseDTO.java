package pe.edu.cibertec.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Respuesta completa de un item del carrito.
 * Si es un pack, incluye la lista de componentes con sus tallas elegidas.
 */
public class CarritoItemResponseDTO {

    private Integer idCarritoItem;
    private String nombreProducto;
    private String talla;              // para productos simples
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private boolean esPack;
    private List<ComponenteResponseDTO> componentes; // para packs

    public CarritoItemResponseDTO() {}

    // Constructor para productos simples
    public CarritoItemResponseDTO(Integer idCarritoItem, String nombreProducto,
                                   String talla, Integer cantidad,
                                   BigDecimal precioUnitario, BigDecimal subtotal) {
        this.idCarritoItem = idCarritoItem;
        this.nombreProducto = nombreProducto;
        this.talla = talla;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.esPack = false;
    }

    // Constructor para packs
    public CarritoItemResponseDTO(Integer idCarritoItem, String nombreProducto,
                                   Integer cantidad, BigDecimal precioUnitario,
                                   BigDecimal subtotal,
                                   List<ComponenteResponseDTO> componentes) {
        this.idCarritoItem = idCarritoItem;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.esPack = true;
        this.componentes = componentes;
    }

    // Getters y setters
    public Integer getIdCarritoItem() { return idCarritoItem; }
    public void setIdCarritoItem(Integer idCarritoItem) { this.idCarritoItem = idCarritoItem; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public boolean isEsPack() { return esPack; }
    public void setEsPack(boolean esPack) { this.esPack = esPack; }

    public List<ComponenteResponseDTO> getComponentes() { return componentes; }
    public void setComponentes(List<ComponenteResponseDTO> componentes) { this.componentes = componentes; }

    // ─── Inner DTO para cada componente del pack ───────────────────────────
    public static class ComponenteResponseDTO {
        private String nombreComponente; // "Hoodie", "Polo 1", etc.
        private String talla;            // "M", "L", etc.

        public ComponenteResponseDTO(String nombreComponente, String talla) {
            this.nombreComponente = nombreComponente;
            this.talla = talla;
        }

        public String getNombreComponente() { return nombreComponente; }
        public String getTalla() { return talla; }
    }
}