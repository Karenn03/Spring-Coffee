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
    public void create(RolesHasPersonasEntity entidad) {

    }

    @Override
    public RolesHasPersonasEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<RolesHasPersonasEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(RolesHasPersonasEntity entidad) {

    }

    @Override
    public void actualizar(RolesHasPersonasEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}
