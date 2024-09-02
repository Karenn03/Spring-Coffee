package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Productos")
public class ProductosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProductos")
    private Integer idProductos;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "stock", nullable = false)
    private Integer stock;


    @ManyToOne
    @JoinColumn(name = "TipoProducto_idTipoProducto", nullable = false)
    private Set<TipoProductoEntity> tipoProducto;

    @OneToMany(mappedBy = "producto")
    private Set<ReseñasEntity> reseñas;

    @OneToMany(mappedBy = "productos")
    private Set<ProductosHasCarritoComprasEntity> productosHasCarritoCompras;

    @OneToMany(mappedBy = "producto")
    private List<VentasHasProductosEntity> ventasHasProductos;

    @OneToMany(mappedBy = "producto")
    private Set<PedidosHasProductosEntity> pedidoHasProductos;

    @OneToMany(mappedBy = "producto")
    private Set<PromocionesHasProductosEntity> promocionesHasProductos;

    // Getters and Setters

}