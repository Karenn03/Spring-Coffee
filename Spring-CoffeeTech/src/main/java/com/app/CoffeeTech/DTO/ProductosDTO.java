package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosDTO {
    private Long idProductos;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Long idTipoProducto;
}