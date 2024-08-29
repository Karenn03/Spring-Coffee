package Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Personas")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonas")
    private Integer idPersonas;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String documento;

    @Column(name = "p_nombre", nullable = false, length = 25)
    private String primerNombre;

    @Column(name = "s_nombre", length = 25)
    private String segundoNombre;

    @Column(name = "p_apellido", nullable = false, length = 25)
    private String primerApellido;

    @Column(name = "s_apellido", length = 25)
    private String segundoApellido;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "direccion", nullable = false, length = 120)
    private String direccion;


    @OneToMany(mappedBy = "personas")
    private List<ReservaEntity> reserva;

    @OneToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private UsuarioEntity usuario;

    // Getters and Setters

}