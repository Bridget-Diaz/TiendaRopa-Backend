package pe.edu.cibertec.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_tallas")
public class ProductoTalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_talla")
    private Integer idProductoTalla;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_talla", nullable = false)
    private Talla talla;

    @Column(name = "stock_por_talla", nullable = false)
    private Integer stockPorTalla = 0;

    // Constructores
    public ProductoTalla() {
    }

    public ProductoTalla(Producto producto, Talla talla, Integer stockPorTalla) {
        this.producto = producto;
        this.talla = talla;
        this.stockPorTalla = stockPorTalla;
    }

    // Getters y Setters
    public Integer getIdProductoTalla() {
        return idProductoTalla;
    }

    public void setIdProductoTalla(Integer idProductoTalla) {
        this.idProductoTalla = idProductoTalla;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    public Integer getStockPorTalla() {
        return stockPorTalla;
    }

    public void setStockPorTalla(Integer stockPorTalla) {
        this.stockPorTalla = stockPorTalla;
    }
}