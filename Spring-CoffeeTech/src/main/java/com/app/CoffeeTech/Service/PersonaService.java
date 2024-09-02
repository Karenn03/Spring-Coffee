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
    public List<PersonaEntity> findAll() {
        return List.of();
    }

    @Override
    public PersonaEntity getById(Long aLong) {
        return null;
    }

    @Override
    public void update(PersonaEntity entity) {
        this.personaRepository.save(entity);
    }

    @Override
    public PersonaEntity save(PersonaEntity entity) {
        return this.personaRepository.save(entity);
    }

    @Override
    public void delete(PersonaEntity entity) {
        this.personaRepository.delete(entity);
    }

    @Override
    public void create(PersonaEntity entity) {
        this.personaRepository.save(entity);
    }
}
