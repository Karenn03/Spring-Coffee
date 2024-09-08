package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoComprasDTO {
    private Long idCarritoCompras;
    private LocalDate fechaAgregado;
    private Long idPersonas;
}