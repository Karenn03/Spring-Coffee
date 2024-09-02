package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidosBusiness {

    @Autowired
    PedidosRepository pedidosRepository;

}