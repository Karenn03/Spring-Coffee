package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VentasBusiness {

    @Autowired
    VentasRepository ventasRepository;

}