package com.app.CoffeeTech.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long idPersonas;

    // NotBlank asegura que el valor no sea null y no sea una cadena vacía o una que solo contenga espacios en blanco
    @NotBlank(message = "El documento es obligatorio.")
    @Size(max = 20, message = "El documento debe tener un máximo de 20 caracteres.")
    private Integer documento;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 50, message = "El nombre debe tener un máximo de 50 caracteres.")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio.")
    @Size(max = 50, message = "El apellido debe tener un máximo de 50 caracteres.")
    private String apellidos;

    @NotBlank(message = "El correo electrónico es obligatorio.")
    @Email(message = "El correo electrónico debe ser válido.")
    private String correoElectronico;

    @NotBlank(message = "La contraseña es obligatoria.")
    @JsonProperty("contraseña")
    private String contraseña;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Size(max = 20, message = "El teléfono debe tener un máximo de 20 caracteres.")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria.")
    @Size(max = 120, message = "La dirección debe tener un máximo de 120 caracteres.")
    private String direccion;

}