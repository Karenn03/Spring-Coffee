package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class ProductosDTO {
    private Integer idProductos;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
}