package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Domicilio")
public class DomicilioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDomicilio")
    private Long idDomicilio;

    @Column(name = "direccion", nullable = false, length = 130)
    private String direccion;

    @Column(name = "especificaciones", nullable = false, length = 250)
    private String especificaciones;

    // Relations
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idPedidos", nullable = false)
    private PedidosEntity pedidos;

}