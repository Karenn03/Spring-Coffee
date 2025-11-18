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
@Table(name = "Privilegios")
public class PrivilegeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrivilegios")
    private Long idPrivilegios;

    @Column(name = "nombre_privilegio", nullable = false, unique = true, length = 50)
    private String nombrePrivilegio;

    // Relations
    @ManyToMany(mappedBy = "privilegios")
    private List<RoleEntity> roles;

}
