package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosDTO {

    private Long idProductos;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 100, message = "El nombre debe tener un máximo de 100 caracteres.")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria.")
    @Size(max = 200, message = "La descripción debe tener un máximo de 200 caracteres.")
    private String descripcion;

    private Double precio;

    // Relations
    @NotNull(message = "El tipo de producto es obligatorio.")
    private Long idTipoProducto;

}