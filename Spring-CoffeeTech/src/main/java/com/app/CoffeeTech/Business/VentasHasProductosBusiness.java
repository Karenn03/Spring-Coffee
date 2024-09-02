package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.VentasHasProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VentasHasProductosBusiness {

    @Autowired
    VentasHasProductosRepository ventasHasProductosRepository;

}