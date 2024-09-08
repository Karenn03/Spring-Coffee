package com.app.CoffeeTech.DTO;

import com.app.CoffeeTech.Entity.PromocionesEntity;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromocionesDTO {
    private Long idPromociones;
    private String nombrePromo;
    private String descripcionPromo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private PromocionesEntity.TipoDescuento tipoDescuento;
    private Double valorDescuento;
    private PromocionesEntity.EstadoPromocion estado;

}