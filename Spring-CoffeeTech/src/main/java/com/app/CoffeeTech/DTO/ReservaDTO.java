package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ReservaDTO {
    private Integer idReserva;
    private LocalDate fecha;
    private LocalTime hora;
    private Integer cantidadPersonas;
}