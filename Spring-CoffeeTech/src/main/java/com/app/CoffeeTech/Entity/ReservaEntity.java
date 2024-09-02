package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private Integer idReserva;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "cantidad_personas", nullable = false)
    private Integer cantidadPersonas;


    @ManyToOne
    @JoinColumn(name = "Personas_idPersonas", nullable = false)
    private PersonaEntity persona;

    @OneToOne(mappedBy = "reserva")
    private List<MesasEntity> mesas;

    // Getters and Setters

}