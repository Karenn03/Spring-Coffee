package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CarritoComprasDTO {
    private Integer idCarritoCompras;
    private LocalDate fechaAgregado;
}