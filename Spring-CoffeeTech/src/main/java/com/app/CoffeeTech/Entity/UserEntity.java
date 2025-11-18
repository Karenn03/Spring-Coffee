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
@Table(name = "Personas")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonas")
    private Long idPersonas;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private Integer documento;

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

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;

    // Breakout Table
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PersonasHasRoles",
            joinColumns = @JoinColumn(name = "idPersonas", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "idRoles", nullable = false))
    private List<RoleEntity> roles;

}
