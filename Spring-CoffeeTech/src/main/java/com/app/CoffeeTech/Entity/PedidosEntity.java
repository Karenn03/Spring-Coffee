package com.app.CoffeeTech.Entity;

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
    @JoinColumn(name = "Personas_idPersonas", nullable = false)
    private PersonaEntity persona;

    @OneToOne(mappedBy = "pedidos")
    private DomicilioEntity domicilio;

    @OneToMany(mappedBy = "pedidos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidosHasProductosEntity> pedidosHasProductos = new ArrayList<>();

    // Getters y Setters

}