package com.app.CoffeeTech.DTO;

import com.app.CoffeeTech.Entity.PromocionesEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromocionesDTO {

    private Long idPromociones;

    @NotBlank(message = "El nombre de la promoción es obligatorio.")
    @Size(max = 100, message = "El nombre de la promoción debe tener un máximo de 100 caracteres.")
    private String nombrePromo;

    @NotBlank(message = "La descripción es obligatoria.")
    @Size(max = 200, message = "La descripción debe tener un máximo de 200 caracteres.")
    private String descripcionPromo;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha de finalización es obligatoria.")
    private LocalDateTime fechaFinal;

    private PromocionesEntity.TipoDescuento tipoDescuento;
    private Double valorDescuento;
    private PromocionesEntity.EstadoPromocion estado;

}