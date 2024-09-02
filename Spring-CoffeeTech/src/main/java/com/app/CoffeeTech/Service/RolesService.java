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
    public void create(RolesEntity entidad) {

    }

    @Override
    public RolesEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<RolesEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(RolesEntity entidad) {

    }

    @Override
    public void actualizar(RolesEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}