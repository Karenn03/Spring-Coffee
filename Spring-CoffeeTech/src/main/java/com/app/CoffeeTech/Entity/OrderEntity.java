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
public class OrderEntity implements Serializable {

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
    private TableEntity mesas;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idVentas", nullable = false)
    private SaleEntity ventas;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idPersonas", nullable = false)
    private UserEntity persona;

    @OneToOne(mappedBy = "pedidos", cascade = CascadeType.REMOVE)
    private DeliveryEntity domicilio;

    // Breakout Table
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PedidosHasProductos",
            joinColumns = @JoinColumn(name = "idPedidos", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idProductos", nullable = false))
    private List<ProductEntity> productos;

}