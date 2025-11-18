package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    private Long idVentas;
    private LocalDate fecha;
    private Long cantidad;

}