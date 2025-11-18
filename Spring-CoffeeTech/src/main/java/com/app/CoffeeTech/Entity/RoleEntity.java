package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoles")
    private Long idRoles;

    @Column(name = "nombre_rol", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEntity.NombreRol nombreRol;

    public enum NombreRol {
        Gerente,
        Barista,
        Panadero,
        Camarero,
        Cliente
    }

    // Relations
    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> personas;

    // Breakout Table
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "RolesHasPrivilegios",
            joinColumns = @JoinColumn(name = "idRoles", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idPrivilegios", nullable = false))
    private List<PrivilegeEntity> privilegios;

}