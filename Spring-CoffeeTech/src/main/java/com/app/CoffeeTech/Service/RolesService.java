package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.RolesEntity;
import com.app.CoffeeTech.Repository.RolesRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RolesService implements IDAO<RolesEntity, Long> {

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public List<RolesEntity> findAll() {
        return List.of();
    }

    @Override
    public RolesEntity getById(Long aLong) {
        return null;
    }

    @Override
    public void update(RolesEntity entity) {
        this.rolesRepository.save(entity);
    }

    @Override
    public RolesEntity save(RolesEntity entity) {
        return this.rolesRepository.save(entity);
    }

    @Override
    public void delete(RolesEntity entity) {
        this.rolesRepository.delete(entity);
    }

    @Override
    public void create(RolesEntity entity) {
        this.rolesRepository.save(entity);
    }
}