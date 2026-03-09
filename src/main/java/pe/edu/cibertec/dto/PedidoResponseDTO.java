package pe.edu.cibertec.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoResponseDTO {

    private Integer idPedido;
    private LocalDateTime fecha;
    private BigDecimal total;
    private String estado;
    private String metodoPago;

    public PedidoResponseDTO() {}

    public PedidoResponseDTO(Integer idPedido, LocalDateTime fecha,
                              BigDecimal total, String estado, String metodoPago) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.metodoPago = metodoPago;
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
}