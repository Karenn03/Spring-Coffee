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
@Table(name = "Personas")
public class PersonaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonas")
    private Long idPersonas;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String documento;

    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @Column(name = "contraseña", nullable = false, length = 50)
    private String contraseña;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "direccion", nullable = false, length = 120)
    private String direccion;

    // Booleans that Spring Security needs
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_no_locked")
    private boolean accountNoLocked;

    @Column(name = "account_no_expired")
    private boolean accountNoExpired;

    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;

    // Relations
    @ManyToMany(mappedBy = "personas")
    private List<RolesEntity> roles;

}