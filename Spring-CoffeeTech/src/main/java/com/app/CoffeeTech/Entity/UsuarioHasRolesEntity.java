package Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Usuario_has_Roles")
public class UsuarioHasRolesEntity {

    @EmbeddedId
    private UsuarioHasRolesId id;

    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "Roles_idRoles", nullable = false)
    private RolesEntity rol;

    // Getters and Setters

    @Embeddable
    public static class UsuarioHasRolesId implements Serializable {

        @Column(name = "Usuario_idUsuario", nullable = false)
        private Integer usuarioIdUsuario;

        @Column(name = "Roles_idRoles", nullable = false)
        private Integer rolesIdRoles;

        // Getters, Setters, hashCode, equals

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UsuarioHasRolesId that = (UsuarioHasRolesId) o;
            return Objects.equals(usuarioIdUsuario, that.usuarioIdUsuario) &&
                   Objects.equals(rolesIdRoles, that.rolesIdRoles);
        }

        @Override
        public int hashCode() {
            return Objects.hash(usuarioIdUsuario, rolesIdRoles);
        }
    }
}