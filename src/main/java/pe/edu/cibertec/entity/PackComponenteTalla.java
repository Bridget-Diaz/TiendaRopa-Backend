package pe.edu.cibertec.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pack_componente_tallas")
public class PackComponenteTalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pack_componente", nullable = false)
    private PackComponente packComponente;

    @ManyToOne
    @JoinColumn(name = "id_talla", nullable = false)
    private Talla talla;

    @Column(name = "stock_por_talla", nullable = false)
    private Integer stockPorTalla = 0;

    // Constructores
    public PackComponenteTalla() {
    }

    public PackComponenteTalla(PackComponente packComponente, Talla talla, Integer stockPorTalla) {
        this.packComponente = packComponente;
        this.talla = talla;
        this.stockPorTalla = stockPorTalla;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PackComponente getPackComponente() {
        return packComponente;
    }

    public void setPackComponente(PackComponente packComponente) {
        this.packComponente = packComponente;
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
