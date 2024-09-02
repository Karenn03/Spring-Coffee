package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.PersonaEntity;
import com.app.CoffeeTech.Repository.PersonaRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PersonaService implements IDAO<PersonaEntity, Long> {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public void create(PersonaEntity entidad) {

    }

    @Override
    public PersonaEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<PersonaEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(PersonaEntity entidad) {

    }

    @Override
    public void actualizar(PersonaEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}
