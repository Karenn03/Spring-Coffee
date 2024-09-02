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
    public List<PedidosHasProductosEntity> findAll() {
        return List.of();
    }

    @Override
    public PedidosHasProductosEntity getById(Long aLong) {
        return null;
    }

    @Override
    public void update(PedidosHasProductosEntity entity) {
        this.pedidosHasProductosRepository.save(entity);
    }

    @Override
    public PedidosHasProductosEntity save(PedidosHasProductosEntity entity) {
        return this.pedidosHasProductosRepository.save(entity);
    }

    @Override
    public void delete(PedidosHasProductosEntity entity) {
        this.pedidosHasProductosRepository.delete(entity);
    }

    @Override
    public void create(PedidosHasProductosEntity entity) {
        this.pedidosHasProductosRepository.save(entity);
    }
}