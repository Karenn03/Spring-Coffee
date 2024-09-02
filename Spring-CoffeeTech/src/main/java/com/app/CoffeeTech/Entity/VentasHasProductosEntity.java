package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Ventas_has_Productos")
public class VentasHasProductosEntity {

    @EmbeddedId
    private VentasHasProductosId id;

    @Column(name = "idVentas_has_Productos")
    private Integer idVentasHasProductos;

    @ManyToOne
    @JoinColumn(name = "Ventas_idVentas", nullable = false)
    private VentasEntity ventas;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos", insertable = false, updatable = false),
            @JoinColumn(name = "Productos_TipoProducto_idTipoProducto", referencedColumnName = "TipoProducto_idTipoProducto", insertable = false, updatable = false)
    })
    private ProductosEntity producto;


    @Embeddable
    public static class VentasHasProductosId implements Serializable {

        @Column(name = "Ventas_idVentas")
        private Integer ventasIdVentas;

        @Column(name = "Productos_idProductos")
        private Integer productosIdProductos;

        @Column(name = "Productos_TipoProductos_idTipoProducto")
        private Integer productosTipoProductosIdTipoProducto;

        // Getters, Setters, hashCode y equals

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VentasHasProductosId that = (VentasHasProductosId) o;
            return Objects.equals(ventasIdVentas, that.ventasIdVentas) &&
                    Objects.equals(productosIdProductos, that.productosIdProductos) &&
                    Objects.equals(productosTipoProductosIdTipoProducto, that.productosTipoProductosIdTipoProducto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ventasIdVentas, productosIdProductos, productosTipoProductosIdTipoProducto);
        }
    }
}