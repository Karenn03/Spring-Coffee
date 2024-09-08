package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromocionesHasProductosDTO {
    private Long idPromocionesHasPoductos;
    private Long idProductos;
    private Long idTipoProducto;
    private Long idPromociones;
}