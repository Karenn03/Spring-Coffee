package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class ProductosHasCarritoComprasDTO {
    private Integer idProductosHasCarritoCompras;
    private Integer productosIdProductos;
    private Integer productosTipoProductoIdTipoProducto;
    private Integer carritoComprasIdCarritoCompras;
    private Integer carritoComprasPersonasIdPersonas;
}