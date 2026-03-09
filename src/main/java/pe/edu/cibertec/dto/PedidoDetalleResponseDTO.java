package pe.edu.cibertec.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDetalleResponseDTO {

    private Integer idPedido;
    private LocalDateTime fecha;
    private BigDecimal total;
    private String estado;
    private String metodoPago;
    private List<ItemDTO> items;

    public PedidoDetalleResponseDTO() {}

    public PedidoDetalleResponseDTO(Integer idPedido, LocalDateTime fecha,
                                     BigDecimal total, String estado,
                                     String metodoPago, List<ItemDTO> items) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.items = items;
    }

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public List<ItemDTO> getItems() { return items; }
    public void setItems(List<ItemDTO> items) { this.items = items; }

    // ─── Inner DTO para cada producto del pedido ───────────────
    public static class ItemDTO {
        private String nombreProducto;
        private String talla;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;

        public ItemDTO(String nombreProducto, String talla,
                       Integer cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
            this.nombreProducto = nombreProducto;
            this.talla = talla;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
            this.subtotal = subtotal;
        }

        public String getNombreProducto() { return nombreProducto; }
        public String getTalla() { return talla; }
        public Integer getCantidad() { return cantidad; }
        public BigDecimal getPrecioUnitario() { return precioUnitario; }
        public BigDecimal getSubtotal() { return subtotal; }
    }
}