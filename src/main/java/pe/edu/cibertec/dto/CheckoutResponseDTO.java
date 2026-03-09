package pe.edu.cibertec.dto;

import java.math.BigDecimal;

public class CheckoutResponseDTO {

    private Integer idPedido;
    private String estado;
    private BigDecimal total;
    private String metodoPago;
    private String mensaje;

    public CheckoutResponseDTO() {}

    public CheckoutResponseDTO(Integer idPedido, String estado,
                                BigDecimal total, String metodoPago, String mensaje) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.total = total;
        this.metodoPago = metodoPago;
        this.mensaje = mensaje;
    }

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}