package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ventas")
public class VentasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVentas")
    private Integer idVentas;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;


    @OneToOne(mappedBy = "ventas")
    private PedidosEntity pedidos;

    @OneToMany(mappedBy = "ventas")
    private List<VentasHasProductosEntity> ventasHasProductos;

}