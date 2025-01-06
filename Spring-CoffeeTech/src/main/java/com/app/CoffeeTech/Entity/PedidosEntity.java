package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Column(name = "fecha_pedido", nullable = false, length = 20)
    private LocalDateTime fechaPedido;

    @Column(name = "total", nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "idMesas")
    private MesasEntity mesas;

    @OneToOne
    @JoinColumn(name = "idVentas")
    private VentasEntity ventas;

    @ManyToOne
    @JoinColumn(name = "idPersonas")
    private PersonaEntity persona;

    @OneToOne(mappedBy = "pedidos", cascade = CascadeType.PERSIST)
    private DomicilioEntity domicilio;

}