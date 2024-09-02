package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class DomicilioDTO {
    private Integer idDomicilio;
    private String direccion;
    private String especificaciones;
}