package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Domicilio")
public class DomicilioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDomicilio")
    private Integer idDomicilio;

    @Column(name = "direccion", nullable = false, length = 120)
    private String direccion;

    @Column(name = "especificaciones", nullable = false, length = 250)
    private String especificaciones;


    @OneToOne
    @JoinColumn(name = "Pedidos_idPedidos", nullable = false)
    private PedidosEntity pedidos;

}