package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Mesas")
public class MesasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMesas")
    private Long idMesas;

    @Column(name = "capacidad", nullable = false)
    private Long capacidad;

    @OneToOne(mappedBy = "mesas")
    private ReservaEntity reserva;

}