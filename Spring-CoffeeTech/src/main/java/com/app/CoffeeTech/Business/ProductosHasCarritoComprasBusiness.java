package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.ProductosHasCarritoComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductosHasCarritoComprasBusiness {

    @Autowired
    ProductosHasCarritoComprasRepository productosHasCarritoComprasRepository;

}