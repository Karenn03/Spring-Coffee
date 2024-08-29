package Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Suscripciones")
public class SuscripcionesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSuscripcion")
    private Integer idSuscripcion;

    @Column(name = "tipo_suscripcion", nullable = false, length = 50)
    private String tipoSuscripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "fecha_suscripcion", nullable = false, length = 20)
    private LocalDateTime fechaSuscripcion;


    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private UsuarioEntity usuario;

    // Getters y Setters

}