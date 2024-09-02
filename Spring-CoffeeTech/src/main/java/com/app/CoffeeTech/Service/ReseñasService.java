package com.app.CoffeeTech.Service;


import com.app.CoffeeTech.Entity.ReseñasEntity;
import com.app.CoffeeTech.Repository.ReseñasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ReseñasService implements IDAO<ReseñasEntity, Long> {

    @Autowired
    ReseñasRepository reseñasRepository;

    @Override
    public void create(ReseñasEntity entidad) {

    }

    @Override
    public ReseñasEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<ReseñasEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(ReseñasEntity entidad) {

    }

    @Override
    public void actualizar(ReseñasEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}
