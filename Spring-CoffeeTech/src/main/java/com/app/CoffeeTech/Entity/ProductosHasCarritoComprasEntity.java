package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Productos_has_CarritoCompras")
public class ProductosHasCarritoComprasEntity {

    @EmbeddedId
    private ProductosHasCarritoComprasId id;

    @Column(name = "idProductos_has_Carrito_Compras", nullable = false)
    private Integer idProductosHasCarritoCompras;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos", insertable = false, updatable = false),
            @JoinColumn(name = "Productos_TipoProducto_idTipoProducto", referencedColumnName = "TipoProducto_idTipoProducto", insertable = false, updatable = false)
    })
    private ProductosEntity productos;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Carrito_Compras_idCarritoCompras", referencedColumnName = "idCarritoCompras", insertable = false, updatable = false),
            @JoinColumn(name = "Carrito_Compras_Personas_idPersonas", referencedColumnName = "Personas_idPersonas", insertable = false, updatable = false)
    })
    private CarritoComprasEntity carritoCompras;


    @Embeddable
    public static class ProductosHasCarritoComprasId implements Serializable {

        @Column(name = "Productos_idProductos", nullable = false)
        private Integer productosIdProductos;

        @Column(name = "Productos_TipoProducto_idTipoProducto", nullable = false)
        private Integer productosTipoProductoIdTipoProducto;

        @Column(name = "Carrito_Compras_idCarritoCompras", nullable = false)
        private Integer carritoComprasIdCarritoCompras;

        @Column(name = "Carrito_Compras_Personas_idPersonas", nullable = false)
        private Integer carritoComprasPersonasIdPersonas;

        // Getters, Setters, hashCode y equals


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProductosHasCarritoComprasId that = (ProductosHasCarritoComprasId) o;
            return Objects.equals(productosIdProductos, that.productosIdProductos) &&
                    Objects.equals(productosTipoProductoIdTipoProducto, that.productosTipoProductoIdTipoProducto) &&
                    Objects.equals(carritoComprasIdCarritoCompras, that.carritoComprasIdCarritoCompras) &&
                    Objects.equals(carritoComprasPersonasIdPersonas, that.carritoComprasPersonasIdPersonas);
        }

        @Override
        public int hashCode() {
            return Objects.hash(productosIdProductos, productosTipoProductoIdTipoProducto, carritoComprasIdCarritoCompras, carritoComprasPersonasIdPersonas);
        }
    }
}