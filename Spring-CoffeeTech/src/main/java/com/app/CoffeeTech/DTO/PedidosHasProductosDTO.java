package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class PedidosHasProductosDTO {
    private Integer idPedidosHasProductos;
    private Integer pedidosIdPedidos;
    private Integer pedidosMesasIdMesas;
    private Integer pedidosVentasIdVentas;
    private Integer pedidosPersonasIdPersonas;
    private Integer productosIdProductos;
    private Integer productosTipoProductoIdTipoProducto;
}