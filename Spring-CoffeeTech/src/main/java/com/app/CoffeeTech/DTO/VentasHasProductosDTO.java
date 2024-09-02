package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class VentasHasProductosDTO {
    private Integer idVentasHasProductos;
    private Integer ventasIdVentas;
    private Integer productosIdProductos;
    private Integer productosTipoProductosIdTipoProducto;
}