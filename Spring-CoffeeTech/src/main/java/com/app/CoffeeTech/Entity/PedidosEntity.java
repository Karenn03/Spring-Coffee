package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pedidos")
public class PedidosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedidos")
    private Long idPedidos;

    @CreationTimestamp
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fechaPedido;

    @Column(name = "total", nullable = false)
    private Double total;

    // Relations
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idMesas", nullable = true)
    private MesasEntity mesas;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idVentas", nullable = false)
    private VentasEntity ventas;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idPersonas", nullable = false)
    private PersonaEntity persona;

    @OneToOne(mappedBy = "pedidos", cascade = CascadeType.REMOVE)
    private DomicilioEntity domicilio;

    // Breakout Table
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PedidosHasProductos",
            joinColumns = @JoinColumn(name = "idPedidos", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idProductos", nullable = false))
    private List<ProductosEntity> productos;

}