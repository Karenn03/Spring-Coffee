package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidosDTO {
    private Long idPedidos;
    private LocalDateTime fechaPedido;
    private Double total;
    private Long idMesas;
    private Long idVentas;
    private Long idPersonas;
}