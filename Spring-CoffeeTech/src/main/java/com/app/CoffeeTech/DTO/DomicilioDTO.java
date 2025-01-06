package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomicilioDTO {

    private Long idDomicilio;

    // NotBlank asegura que el valor no sea null y no sea una cadena vacía o una que solo contenga espacios en blanco
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 130, message = "La dirección debe tener un máximo de 130 caracteres")
    private String direccion;

    @NotBlank(message = "Las especificaciones son obligatorias")
    @Size(max = 250, message = "Las especificaciones deben tener un máximo de 250 caracteres")
    private String especificaciones;

    private Long idPedidos;

}