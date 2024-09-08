package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentasHasProductosDTO {
    private Long idVentasHasProductos;
    private Long idVentas;
    private Long idProductos;
    private Long idTipoProducto;
}