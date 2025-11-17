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

    @Column(name = "nombre_rol", nullable = false)
    @Enumerated(EnumType.STRING)
    private RolesEntity.NombreRol nombreRol;

    public enum NombreRol {
        Gerente,
        Barista,
        Panadero,
        Camarero,
        Cliente
    }

    // Relations
    @ManyToMany(mappedBy = "roles")
    private List<PrivilegiosEntity> privilegios;

    // Breakout Table
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "RolesHasPersonas",
            joinColumns = @JoinColumn(name = "idRoles", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idPersonas", nullable = false))
    private List<PersonaEntity> personas;

}