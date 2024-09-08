package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosHasCarritoComprasDTO {
    private Long idProductosHasCarritoCompras;
    private Long idCarritoCompras;
    private Long idPersonas;
    private Long idProductos;
    private Long idTipoProducto;
}