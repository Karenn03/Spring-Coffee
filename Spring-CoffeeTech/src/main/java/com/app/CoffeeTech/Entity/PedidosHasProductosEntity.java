package Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Pedidos_has_Productos")
public class PedidosHasProductosEntity {

    @EmbeddedId
    private PedidosHasProductosId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Pedidos_idPedidos", referencedColumnName = "idPedidos", insertable = false, updatable = false),
            @JoinColumn(name = "Pedidos_Mesas_idMesas", referencedColumnName = "Mesas_idMesas", insertable = false, updatable = false),
            @JoinColumn(name = "Pedidos_Ventas_idVentas", referencedColumnName = "Ventas_idVentas", insertable = false, updatable = false),
            @JoinColumn(name = "Pedidos_Usuario_idUsuario", referencedColumnName = "Usuario_idUsuario", insertable = false, updatable = false)
    })
    private PedidosEntity pedidos;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos", insertable = false, updatable = false),
            @JoinColumn(name = "Productos_TipoProducto_idTipoProducto", referencedColumnName = "TipoProducto_idTipoProducto", insertable = false, updatable = false)
    })
    private ProductosEntity productos;

    // Getters and Setters

    @Embeddable
    public static class PedidosHasProductosId implements Serializable {

        @Column(name = "Pedidos_idPedidos")
        private Integer pedidosIdPedidos;

        @Column(name = "Pedidos_Mesas_idMesas")
        private Integer pedidosMesasIdMesas;

        @Column(name = "Pedidos_Ventas_idVentas")
        private Integer pedidosVentasIdVentas;

        @Column(name = "Pedidos_Usuario_idUsuario")
        private Integer pedidosUsuarioIdUsuario;

        @Column(name = "Productos_idProductos")
        private Integer productosIdProductos;

        @Column(name = "Productos_TipoProducto_idTipoProducto")
        private Integer productosTipoProductoIdTipoProducto;

        // Getters, Setters, hashCode y equals

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PedidosHasProductosId that = (PedidosHasProductosId) o;
            return Objects.equals(pedidosIdPedidos, that.pedidosIdPedidos) &&
                   Objects.equals(pedidosMesasIdMesas, that.pedidosMesasIdMesas) &&
                   Objects.equals(pedidosVentasIdVentas, that.pedidosVentasIdVentas) &&
                   Objects.equals(pedidosUsuarioIdUsuario, that.pedidosUsuarioIdUsuario) &&
                   Objects.equals(productosIdProductos, that.productosIdProductos) &&
                   Objects.equals(productosTipoProductoIdTipoProducto, that.productosTipoProductoIdTipoProducto);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pedidosIdPedidos, pedidosMesasIdMesas, pedidosVentasIdVentas, pedidosUsuarioIdUsuario, productosIdProductos, productosTipoProductoIdTipoProducto);
        }
    }
}
