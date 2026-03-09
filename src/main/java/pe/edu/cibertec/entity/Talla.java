package pe.edu.cibertec.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tallas")
public class Talla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_talla")
    private Integer idTalla;

    @Column(name = "nombre_talla", nullable = false, unique = true, length = 10)
    private String nombreTalla;

    @Column(nullable = false)
    private Integer orden = 0;

    @OneToMany(mappedBy = "talla", cascade = CascadeType.ALL)
    private List<ProductoTalla> productos;

    // Constructores
    public Talla() {
    }

    public Talla(String nombreTalla, Integer orden) {
        this.nombreTalla = nombreTalla;
        this.orden = orden;
    }

    // Getters y Setters
    public Integer getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Integer idTalla) {
        this.idTalla = idTalla;
    }

    public String getNombreTalla() {
        return nombreTalla;
    }

    public void setNombreTalla(String nombreTalla) {
        this.nombreTalla = nombreTalla;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public List<ProductoTalla> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoTalla> productos) {
        this.productos = productos;
    }
}