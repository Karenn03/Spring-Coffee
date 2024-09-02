package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.MesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MesasBusiness {

    @Autowired
    MesasRepository mesasRepository;

}