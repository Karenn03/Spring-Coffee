package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReseñasDTO {

    private Long idReseñas;

    private Long calificacion;

    @NotBlank(message = "El comentario es obligatorio.")
    @Size(max = 200, message = "El comentario debe tener un máximo de 200 caracteres.")
    private String comentario;

    private LocalDateTime fechaReseña;

    // Relations
    @NotNull(message = "La persona es obligatoria.")
    private Long idPersonas;

    private Long idProductos;
    private Long idTipoProducto;

}