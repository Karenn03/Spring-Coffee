package Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pedidos")
public class PedidosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedidos")
    private Integer idPedidos;

    @Column(name = "fecha_pedido", nullable = false, length = 20)
    private LocalDateTime fechaPedido;

    @Column(name = "total", nullable = false)
    private Double total;


    @ManyToOne
    @JoinColumn(name = "Mesas_idMesas", nullable = false)
    private MesasEntity mesas;

    @OneToOne
    @JoinColumn(name = "Ventas_idVentas", nullable = false)
    private VentasEntity ventas;

    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private UsuarioEntity usuario;

    @OneToOne(mappedBy = "pedido")
    private DomicilioEntity domicilio;

    @OneToMany(mappedBy = "pedidosEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidosHasProductosEntity> pedidosHasProductosEntities = new ArrayList<>();

    // Getters y Setters

}