package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ReservaEntity;
import com.app.CoffeeTech.Repository.ReservaRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ReservaService implements IDAO<ReservaEntity, Long> {

    @Autowired
    ReservaRepository reservaRepository;

    @Override
    public void create(ReservaEntity entidad) {

    }

    @Override
    public ReservaEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<ReservaEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(ReservaEntity entidad) {

    }

    @Override
    public void actualizar(ReservaEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}
