package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReseñaDTO {
    private Integer idReseñas;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime fechaReseña;
}