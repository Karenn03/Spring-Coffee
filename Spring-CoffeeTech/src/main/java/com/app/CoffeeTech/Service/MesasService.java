package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.MesasEntity;
import com.app.CoffeeTech.Repository.MesasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class MesasService implements IDAO<MesasEntity, Long> {

    @Autowired
    MesasRepository mesasRepository;

    @Override
    public void create(MesasEntity entidad) {

    }

    @Override
    public MesasEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<MesasEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(MesasEntity entidad) {

    }

    @Override
    public void actualizar(MesasEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}