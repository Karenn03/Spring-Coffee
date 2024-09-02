package com.app.CoffeeTech.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PedidosDTO {
    private Integer idPedidos;
    private LocalDateTime fechaPedido;
    private Double total;
}