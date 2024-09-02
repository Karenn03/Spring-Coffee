package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.RolesHasPersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolesHasPersonasBusiness {

    @Autowired
    RolesHasPersonasRepository rolesHasPersonasRepository;

}