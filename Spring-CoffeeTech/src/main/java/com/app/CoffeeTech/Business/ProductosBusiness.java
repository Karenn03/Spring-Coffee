package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductosBusiness {

    @Autowired
    ProductosRepository productosRepository;

}