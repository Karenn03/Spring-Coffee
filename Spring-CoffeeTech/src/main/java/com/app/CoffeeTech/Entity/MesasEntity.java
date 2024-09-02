package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Mesas")
public class MesasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMesas")
    private Integer idMesas;

    @Column(name = "numero_mesa", nullable = false)
    private Integer numeroMesa;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;


    @OneToMany(mappedBy = "mesas")
    private List<PedidosEntity> pedidos;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "Reserva_idReserva", referencedColumnName = "idReserva"),
            @JoinColumn(name = "Reserva_Personas_idPersonas", referencedColumnName = "Personas_idPersonas")
    })
    private ReservaEntity reserva;

}