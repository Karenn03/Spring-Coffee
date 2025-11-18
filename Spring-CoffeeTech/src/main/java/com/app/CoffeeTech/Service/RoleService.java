package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.RoleEntity;
import com.app.CoffeeTech.Repository.RoleRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class RoleService implements IDAO<RoleEntity, Long> {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Page<RoleEntity> findAll(PageRequest pageRequest) {
        return roleRepository.findAll(pageRequest);
    }

    @Override
    public RoleEntity getById(Long id) {
        Optional<RoleEntity> optionalRoles = roleRepository.findById(id);
        return optionalRoles.orElse(null);
    }

    @Override
    public void update(RoleEntity entity) {
        this.roleRepository.save(entity);
    }

    @Override
    public RoleEntity save(RoleEntity entity) {
        return this.roleRepository.save(entity);
    }

    @Override
    public void delete(RoleEntity entity) {
        roleRepository.delete(entity);
    }

    @Override
    public void create(RoleEntity entity) {
        this.roleRepository.save(entity);
    }

    public RoleEntity findByNombreRol(String nombreRol) {
        return roleRepository.findByNombreRol(nombreRol);
    }

}