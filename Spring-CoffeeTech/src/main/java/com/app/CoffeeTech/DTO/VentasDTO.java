package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VentasDTO {
    private Integer idVentas;
    private LocalDate fecha;
    private Integer cantidad;
}