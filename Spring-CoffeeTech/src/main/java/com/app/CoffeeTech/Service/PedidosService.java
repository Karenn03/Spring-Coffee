package com.app.CoffeeTech.Service;


import com.app.CoffeeTech.Entity.PedidosEntity;
import com.app.CoffeeTech.Repository.PedidosRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PedidosService implements IDAO<PedidosEntity, Long> {

    @Autowired
    PedidosRepository pedidosRepository;

    @Override
    public void create(PedidosEntity entidad) {

    }

    @Override
    public PedidosEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<PedidosEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(PedidosEntity entidad) {

    }

    @Override
    public void actualizar(PedidosEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}
