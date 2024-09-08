package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private Long idReserva;
    private LocalDate fecha;
    private LocalTime hora;
    private Long cantidadPersonas;
    private Long idPersonas;
    private Long idMesas;
}