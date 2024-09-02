package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
public class RolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoles")
    private Integer idRoles;

    @Column(name = "nombre_rol", nullable = false, length = 50)
    private String nombreRol;


    @OneToMany(mappedBy = "rol")
    private List<RolesHasPersonasEntity> rolesHasPersonas;

}