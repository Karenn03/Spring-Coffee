package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    private Long idReserva;

    @NotNull(message = "La fecha es obligatoria.")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria.")
    private LocalTime hora;

    @Min(value = 1, message = "La cantidad de personas debe ser mayor o igual a 1")
    private Long cantidadPersonas;

    // Relations
    @NotNull(message = "La persona es obligatoria.")
    private Long idPersonas;

    @NotNull(message = "La mesa es obligatoria.")
    private Long idMesas;

}