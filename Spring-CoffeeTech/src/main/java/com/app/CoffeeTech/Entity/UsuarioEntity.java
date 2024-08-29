package Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 50, unique = true)
    private String nombreUsuario;

    @Column(name = "password", nullable = false, length = 50)
    private String password;


    @OneToOne
    @JoinColumn(name = "CarritoCompras_idCarritoCompras", nullable = false)
    private CarritoEntity carritoCompras;

    @OneToOne(mappedBy = "usuario")
    private PersonaEntity persona;

    @OneToMany(mappedBy = "usuario")
    private List<SuscripcionesEntity> suscripcion;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioHasRolesEntity> usuarioHasRoles;

    @OneToMany(mappedBy = "usuario")
    private List<PedidosEntity> pedidos;

    @OneToMany(mappedBy = "usuario")
    private List<ReseñasEntity> reseñas;

    // Getters and Setters

}