package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaBusiness {

    @Autowired
    ReservaRepository reservaRepository;

}