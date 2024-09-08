package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.RolesEntity;
import com.app.CoffeeTech.Repository.RolesRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService implements IDAO<RolesEntity, Long> {

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public List<RolesEntity> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public RolesEntity getById(Long id) {
        Optional<RolesEntity> optionalRoles = rolesRepository.findById(id);
        return optionalRoles.orElse(null);
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
        rolesRepository.delete(entity);
    }

    @Override
    public void create(RolesEntity entity) {
        this.rolesRepository.save(entity);
    }

    public RolesEntity findByRolName(String nombreRol) {
        return rolesRepository.findByRolName(nombreRol);
    }
}