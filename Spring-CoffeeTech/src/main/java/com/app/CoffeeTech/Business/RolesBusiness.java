package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolesBusiness {

    @Autowired
    RolesRepository rolesRepository;

}