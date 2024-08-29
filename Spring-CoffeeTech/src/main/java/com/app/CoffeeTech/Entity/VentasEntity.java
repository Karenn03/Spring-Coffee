package Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Ventas")
public class VentasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVentas")
    private Integer idVentas;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;


    @OneToOne(mappedBy = "ventas")
    private PedidosEntity pedidos;

    @OneToMany(mappedBy = "ventas")
    private List<DetalleVentaEntity> detallesVenta;

    // Getters y Setters

}