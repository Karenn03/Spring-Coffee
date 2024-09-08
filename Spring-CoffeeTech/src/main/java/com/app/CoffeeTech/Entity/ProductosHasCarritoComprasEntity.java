package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Productos_has_CarritoCompras")
public class ProductosHasCarritoComprasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProductos_has_Carrito_Compras")
    private Long idProductosHasCarritoCompras;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idCarritoCompras", referencedColumnName = "idCarritoCompras", insertable = false, updatable = false),
            @JoinColumn(name = "idPersonas", referencedColumnName = "idPersonas", insertable = false, updatable = false)
    })
    private CarritoComprasEntity carritoCompras;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idProductos", referencedColumnName = "idProductos", insertable = false, updatable = false),
            @JoinColumn(name = "idTipoProducto", referencedColumnName = "idTipoProducto", insertable = false, updatable = false)
    })
    private ProductosEntity producto;

}