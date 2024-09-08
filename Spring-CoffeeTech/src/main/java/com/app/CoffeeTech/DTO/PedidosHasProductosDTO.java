package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidosHasProductosDTO {
    private Long idPedidosHasProductos;
    private Long idPedidos;
    private Long idMesas;
    private Long idVentas;
    private Long idPersonas;
    private Long idProductos;
}