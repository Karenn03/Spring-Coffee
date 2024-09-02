package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.PedidosHasProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidosHasProductosBusiness {

    @Autowired
    PedidosHasProductosRepository pedidosHasProductosRepository;

}
