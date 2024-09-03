package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "persona_id", nullable = false) // Nombre de columna en la tabla 'reserva'
    private PersonaEntity persona;

    @OneToMany(mappedBy = "reserva")
    private List<MesasEntity> mesas;

}