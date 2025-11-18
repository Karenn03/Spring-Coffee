package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDTO {

    private Long idMesas;

    @NotNull(message = "El número de la mesa es obligatorio.")
    @Min(value = 1, message = "El número de la mesa debe ser mayor o igual a 1")
    private Long numero;

    @NotNull(message = "El estado es obligatorio")
    private Boolean state;

}