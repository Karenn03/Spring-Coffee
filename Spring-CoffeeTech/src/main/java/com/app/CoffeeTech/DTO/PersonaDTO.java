package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class PersonaDTO {
    private Integer idPersonas;
    private String documento;
    private String nombreUsuario;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String direccion;
}