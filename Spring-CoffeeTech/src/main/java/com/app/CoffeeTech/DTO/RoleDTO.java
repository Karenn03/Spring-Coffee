package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private Long idRoles;

    @NotBlank(message = "El nombre del rol es obligatorio.")
    @Size(max = 50, message = "El nombre del rol debe tener un máximo de 50 caracteres.")
    private String nombreRol;

}