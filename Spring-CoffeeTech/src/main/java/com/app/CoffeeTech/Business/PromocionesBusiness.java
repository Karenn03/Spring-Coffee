package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.PromocionesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromocionesBusiness {

    @Autowired
    PromocionesRespository promocionesRespository;

}