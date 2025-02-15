package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reserva")
public class ReservaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private Long idReserva;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "cantidad_personas", nullable = false)
    private Long cantidadPersonas;

    // Relations
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idPersonas", nullable = false)
    private PersonaEntity persona;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idMesas", nullable = false)
    private MesasEntity mesas;

}