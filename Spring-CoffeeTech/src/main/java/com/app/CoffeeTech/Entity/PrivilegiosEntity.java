package com.app.CoffeeTech.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Privilegios")
public class PrivilegiosEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrivilegios")
    private Long idPrivilegios;

    @Column(name = "nombre_privilegio", nullable = false, unique = true, length = 50)
    private String nombrePrivilegio;

    // Breakout Table
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PrivilegiosHasRoles",
            joinColumns = @JoinColumn(name = "idPrivilegios", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idRoles", nullable = false))
    private List<RolesEntity> roles;

}
