package pe.edu.cibertec.dto.admin;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AdminPedidoResponseDTO {
    private Integer idPedido;
    private String nombreCliente;
    private String emailCliente;
    private LocalDateTime fecha;
    private BigDecimal total;
    private String estado;
    private String ciudad;
    private String tipoEntrega;
    private List<ItemDTO> items;

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getEmailCliente() { return emailCliente; }
    public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getTipoEntrega() { return tipoEntrega; }
    public void setTipoEntrega(String tipoEntrega) { this.tipoEntrega = tipoEntrega; }
    public List<ItemDTO> getItems() { return items; }
    public void setItems(List<ItemDTO> items) { this.items = items; }

    //Inner DTO para items del pedido 
    // si me preguntan yo solo dire haci lo quiso nuestro señor
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




