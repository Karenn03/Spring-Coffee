package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class PromocionesHasProductosDTO {
    private Integer idPromocionesHasPoductos;
    private Integer promocionesIdPromociones;
    private Integer productosIdProductos;
    private Integer productosTipoProductoIdTipoProducto;
}