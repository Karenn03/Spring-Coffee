package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Productos")
public class ProductosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProductos")
    private Long idProductos;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Double precio;

    // Relations
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idTipoProducto", nullable = false)
    private TipoProductoEntity tipoProducto;

    @ManyToMany(mappedBy = "productos", cascade = CascadeType.PERSIST)
    private List<PedidosEntity> pedidos;

    // Breakout Table
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ProductosHasPromociones",
            joinColumns = @JoinColumn(name = "idProductos", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idPromociones", nullable = false)
    )
    private List<PromocionesEntity> promociones;

}