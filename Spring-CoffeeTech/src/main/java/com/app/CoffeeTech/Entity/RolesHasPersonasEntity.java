package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Roles_has_Personas")
public class RolesHasPersonasEntity {

    @EmbeddedId
    private RolesHasPersonasId id;

    @Column(name = "idRoles_has_Personas")
    private Integer idRolesHasPersonas;

    @ManyToOne
    @JoinColumn(name = "Roles_idRoles", nullable = false)
    private RolesEntity roles;

    @ManyToOne
    @JoinColumn(name = "Personas_idPersonas", nullable = false)
    private PersonaEntity persona;


    @Embeddable
    public static class RolesHasPersonasId implements Serializable {

        @Column(name = "Roles_idRoles")
        private Integer rolesIdRoles;

        @Column(name = "Personas_idPersonas")
        private Integer personasIdPersonas;

        // Getters, Setters, hashCode y equals

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RolesHasPersonasId that = (RolesHasPersonasId) o;
            return Objects.equals(rolesIdRoles, that.rolesIdRoles) &&
                    Objects.equals(personasIdPersonas, that.personasIdPersonas);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rolesIdRoles, personasIdPersonas);
        }
    }
}