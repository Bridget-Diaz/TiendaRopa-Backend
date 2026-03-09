package pe.edu.cibertec.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_item_componentes")
public class CarritoItemComponente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_carrito_item", nullable = false)
    private CarritoItem carritoItem;

    @ManyToOne
    @JoinColumn(name = "id_pack_componente", nullable = false)
    private PackComponente packComponente;

    @ManyToOne
    @JoinColumn(name = "id_talla", nullable = false)
    private Talla talla;

    public CarritoItemComponente() {}

    public CarritoItemComponente(CarritoItem carritoItem,
                                  PackComponente packComponente,
                                  Talla talla) {
        this.carritoItem = carritoItem;
        this.packComponente = packComponente;
        this.talla = talla;
    }

    public Integer getId() { return id; }

    public CarritoItem getCarritoItem() { return carritoItem; }
    public void setCarritoItem(CarritoItem carritoItem) { this.carritoItem = carritoItem; }

    public PackComponente getPackComponente() { return packComponente; }
    public void setPackComponente(PackComponente packComponente) { this.packComponente = packComponente; }

    public Talla getTalla() { return talla; }
    public void setTalla(Talla talla) { this.talla = talla; }
}