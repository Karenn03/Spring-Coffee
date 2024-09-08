package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomicilioDTO {
    private Long idDomicilio;
    private String direccion;
    private String especificaciones;
    private Long idPedidos;
}