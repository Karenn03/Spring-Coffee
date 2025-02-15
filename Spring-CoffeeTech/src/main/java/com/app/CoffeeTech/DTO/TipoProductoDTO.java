package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoProductoDTO {

    private Long idTipoProducto;

    @NotBlank(message = "El nombre del tipo de producto es obligatorio.")
    @Size(max = 100, message = "El nombre del tipo de producto debe tener un máximo de 100 caracteres.")
    private String nombreTipoProd;

}