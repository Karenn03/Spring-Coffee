package Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Promociones_has_Productos")
public class PromocionesHasProductosEntity {

    @EmbeddedId
    private PromocionesHasProductosId id;

    @ManyToOne
    @JoinColumn(name = "Promociones_idPromociones", insertable = false, updatable = false)
    private PromocionesEntity promocion;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos", insertable = false, updatable = false),
            @JoinColumn(name = "Productos_TipoProducto_idTipoProducto", referencedColumnName = "TipoProducto_idTipoProducto", insertable = false, updatable = false)
    })
    private ProductosEntity producto;

    // Getters and Setters

    @Embeddable
    public static class PromocionesHasProductosId implements Serializable {

        @Column(name = "Promociones_idPromociones")
        private Integer promocionesIdPromociones;

        @Column(name = "Productos_idProductos")
        private Integer productosIdProductos;

        @Column(name = "Productos_TipoProducto_idTipoProducto")
        private Integer productosTipoProductoIdTipoProducto;

        // Getters, Setters, hashCode y equals

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PromocionesHasProductosId that = (PromocionesHasProductosId) o;
            return Objects.equals(promocionesIdPromociones, that.promocionesIdPromociones) &&
                   Objects.equals(productosIdProductos, that.productosIdProductos) &&
                   Objects.equals(productosTipoProductoIdTipoProducto, that.productosTipoProductoIdTipoProducto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(promocionesIdPromociones, productosIdProductos, productosTipoProductoIdTipoProducto);
        }
    }
}