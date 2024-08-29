package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Detalle_Venta")
public class DetalleVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleVenta")
    private Integer idDetalleVenta;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;


    @ManyToOne
    @JoinColumn(name = "Ventas_idVentas", nullable = false)
    private VentasEntity ventas;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "Productos_idProductos", referencedColumnName = "idProductos"),
            @JoinColumn(name = "Productos_TipoProducto_idTipoProducto", referencedColumnName = "TipoProducto_idTipoProducto")
    })
    private ProductosEntity producto;

    // Getters and Setters
}