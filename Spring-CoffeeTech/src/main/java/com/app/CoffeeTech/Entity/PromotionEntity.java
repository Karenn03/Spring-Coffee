package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Promociones")
public class PromotionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPromociones")
    private Long idPromociones;

    @Column(name = "nombre_promocion", nullable = false, length = 100)
    private String nombrePromocion;

    @Column(name = "descripcion_promocion", nullable = false, length = 200)
    private String descripcionPromocion;

    @Column(name = "fecha_inicio", nullable = false, length = 20)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_final", nullable = false, length = 20)
    private LocalDateTime fechaFinal;

    @Column(name = "tipo_descuento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDescuento tipoDescuento;

    @Column(name = "valor_descuento", nullable = false)
    private Double valorDescuento;

    @Column(name = "estado_promocion", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPromocion estadoPromocion;

    public enum TipoDescuento {
        Descuento_por_Cantidad,
        Descuento_por_Temporada,
        Descuento_por_Fidelidad,
        Descuento_de_Lanzamiento,
        Descuento_por_Compra_Mínima
    }

    public enum EstadoPromocion {
        activa,
        inactiva,
        programada,
        suspendida
    }

}