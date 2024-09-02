package com.app.CoffeeTech.Service;


import com.app.CoffeeTech.Entity.PedidosHasProductosEntity;
import com.app.CoffeeTech.Repository.PedidosHasProductosRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PedidosHasProductosService implements IDAO<PedidosHasProductosEntity, Long> {

    @Autowired
    PedidosHasProductosRepository pedidosHasProductosRepository;

    @Override
    public void create(PedidosHasProductosEntity entidad) {

    }

    @Override
    public PedidosHasProductosEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<PedidosHasProductosEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(PedidosHasProductosEntity entidad) {

    }

    @Override
    public void actualizar(PedidosHasProductosEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}