package com.app.CoffeeTech.DTO;

import lombok.*;

@Data
@AllArgsConstructor
public class RolesHasPersonasDTO {
    private Integer idRolesHasPersonas;
    private Integer rolesIdRoles;
    private Integer personasIdPersonas;
}