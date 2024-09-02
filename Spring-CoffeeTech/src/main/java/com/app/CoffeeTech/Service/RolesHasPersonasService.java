package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.RolesHasPersonasEntity;
import com.app.CoffeeTech.Repository.RolesHasPersonasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RolesHasPersonasService implements IDAO<RolesHasPersonasEntity, Long> {

    @Autowired
    RolesHasPersonasRepository rolesHasPersonasRepository;

    @Override
    public List<RolesHasPersonasEntity> findAll() {
        return List.of();
    }

    @Override
    public RolesHasPersonasEntity getById(Long aLong) {
        return null;
    }

    @Override
    public void update(RolesHasPersonasEntity entity) {
        this.rolesHasPersonasRepository.save(entity);
    }

    @Override
    public RolesHasPersonasEntity save(RolesHasPersonasEntity entity) {
        return this.rolesHasPersonasRepository.save(entity);
    }

    @Override
    public void delete(RolesHasPersonasEntity entity) {
        this.rolesHasPersonasRepository.delete(entity);
    }

    @Override
    public void create(RolesHasPersonasEntity entity) {
        this.rolesHasPersonasRepository.save(entity);
    }
}