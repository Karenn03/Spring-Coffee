package com.app.CoffeeTech.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long idPedidos;

    private LocalDateTime fechaPedido;
    private Double total;

    // Relations
    private Long idMesas;

    @NotNull(message = "La venta es obligatoria.")
    private Long idVentas;

    @NotNull(message = "La persona es obligatoria.")
    private Long idPersonas;

}