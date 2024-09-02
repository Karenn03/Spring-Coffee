package com.app.CoffeeTech.Business;


import com.app.CoffeeTech.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonaBusiness {

    @Autowired
    PersonaRepository personaRepository;

}