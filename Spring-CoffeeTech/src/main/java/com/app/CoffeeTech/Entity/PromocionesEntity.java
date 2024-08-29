package Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Promociones")
public class PromocionesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPromociones")
    private Integer idPromociones;

    @Column(name = "nombre_promo", nullable = false, length = 100)
    private String nombrePromo;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "fecha_inicio", nullable = false, length = 20)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_final", nullable = false, length = 20)
    private LocalDateTime fechaFinal;

    @Column(name = "tipo_descuento", nullable = false, length = 50)
    private String tipoDescuento;

    @Column(name = "valor_descuento", nullable = false)
    private Double valorDescuento;

    @Enumerated(EnumType.STRING)
    private EstadoPromocion estado;

    public enum EstadoPromocion {
        activa,
        inactiva,
        programada,
        suspendida
    }


    @OneToMany(mappedBy = "promocion")
    private Set<PromocionesHasProductosEntity> promocionesHasProductos;

    // Getters and Setters

}