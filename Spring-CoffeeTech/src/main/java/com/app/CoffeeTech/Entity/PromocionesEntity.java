package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Promociones")
public class PromocionesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPromociones")
    private Long idPromociones;

    @Column(name = "nombre_promo", nullable = false, length = 100)
    private String nombrePromo;

    @Column(name = "descripcion_promo", nullable = false, length = 200)
    private String descripcionPromo;

    @Column(name = "fecha_inicio", nullable = false, length = 20)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_final", nullable = false, length = 20)
    private LocalDateTime fechaFinal;

    @Enumerated(EnumType.STRING)
    private TipoDescuento tipo_descuento;

    @Column(name = "valor_descuento", nullable = false)
    private Double valorDescuento;

    @Enumerated(EnumType.STRING)
    private EstadoPromocion estado;

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