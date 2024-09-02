package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.CarritoComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarritoComprasBusiness {

    @Autowired
    CarritoComprasRepository carritoComprasRepository;

}