package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private Long idPersonas;
    private String documento;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String contraseña;
    private String telefono;
    private String direccion;
}