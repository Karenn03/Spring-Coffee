package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReseñasDTO {
    private Long idReseñas;
    private Long calificacion;
    private String comentario;
    private LocalDateTime fechaReseña;
    private Long idPersonas;
    private Long idProductos;
    private Long idTipoProducto;
}