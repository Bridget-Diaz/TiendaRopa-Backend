package pe.edu.cibertec.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pack_componentes")
public class PackComponente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pack_componente")
    private Integer idPackComponente;

    @ManyToOne
    @JoinColumn(name = "id_producto_pack", nullable = false)
    private Producto productoPack;

    @Column(name = "nombre_componente", nullable = false)
    private String nombreComponente;

    @OneToMany(mappedBy = "packComponente", fetch = FetchType.LAZY)
    private List<PackComponenteTalla> tallas = new ArrayList<>();

    public PackComponente() {}

    public Integer getIdPackComponente() { return idPackComponente; }
    public void setIdPackComponente(Integer id) { this.idPackComponente = id; }

    public Producto getProductoPack() { return productoPack; }
    public void setProductoPack(Producto p) { this.productoPack = p; }

    public String getNombreComponente() { return nombreComponente; }
    public void setNombreComponente(String n) { this.nombreComponente = n; }

    public List<PackComponenteTalla> getTallas() { return tallas; }
}