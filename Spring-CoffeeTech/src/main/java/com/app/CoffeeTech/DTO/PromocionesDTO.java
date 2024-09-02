package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PromocionesDTO {
    private Integer idPromociones;
    private String nombrePromo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private String tipoDescuento;
    private Double valorDescuento;
    private EstadoPromocion estado;

    public enum EstadoPromocion {
        activa,
        inactiva,
        programada,
        suspendida
    }

}