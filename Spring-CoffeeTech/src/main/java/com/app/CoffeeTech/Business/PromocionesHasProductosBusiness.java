package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.PromocionesHasProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromocionesHasProductosBusiness {

    @Autowired
    PromocionesHasProductosRepository promocionesHasProductosRepository;

}