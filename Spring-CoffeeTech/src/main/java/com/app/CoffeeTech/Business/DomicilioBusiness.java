package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomicilioBusiness {

    @Autowired
    DomicilioRepository domicilioRepository;

}