package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TipoProductoBusiness {

    @Autowired
    TipoProductoRepository tipoProductoRepository;

}