package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles_has_Personas")
public class RolesHasPersonasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoles_has_Personas")
    private Long idRolesHasPersonas;

    @ManyToOne
    @JoinColumn(name = "idPersonas", nullable = false)
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "idRoles", nullable = false)
    private RolesEntity rol;

}