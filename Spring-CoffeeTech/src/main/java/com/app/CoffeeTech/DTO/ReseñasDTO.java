package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReseñasDTO {
    private Integer idReseñas;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime fechaReseña;
}