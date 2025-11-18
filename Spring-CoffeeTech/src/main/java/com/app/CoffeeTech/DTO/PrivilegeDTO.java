package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeDTO {

    private Long idPrivilegios;

    @NotBlank(message = "El nombre del privilegio es obligatorio.")
    @Size(max = 50, message = "El nombre del privilegio debe tener un máximo de 50 caracteres.")
    private String nombrePrivilegio;

}
