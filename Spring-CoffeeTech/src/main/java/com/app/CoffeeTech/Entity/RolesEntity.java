package Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Roles")
public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoles")
    private Integer idRoles;

    @Column(name = "nombre_rol", nullable = false, length = 50)
    private String nombreRol;


    @OneToMany(mappedBy = "rol")
    private List<UsuarioHasRolesEntity> usuarioHasRoles;

    // Getters and Setters

}