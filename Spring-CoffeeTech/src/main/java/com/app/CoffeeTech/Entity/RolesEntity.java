package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
public class RolesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoles")
    private Long idRoles;

    @Column(name = "nombre_rol", nullable = false, length = 50)
    private String nombreRol;

    // Breakout Table
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "PersonasHasRoles",
            joinColumns = @JoinColumn(name = "idRoles", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idPersonas", nullable = false))
    private List<PersonaEntity> personas;

}