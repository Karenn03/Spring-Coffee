package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesasDTO {

    private Long idMesas;

    @NotNull(message = "La capacidad es obligatoria.")
    @Min(value = 1, message = "La capacidad debe ser mayor o igual a 1")
    private Long capacidad;

    @NotNull(message = "El estado es obligatorio")
    private Boolean state;

}