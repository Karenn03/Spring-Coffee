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
    public List<PedidosEntity> findAll() {
        return List.of();
    }

    @Override
    public PedidosEntity getById(Long aLong) {
        return null;
    }

    @Override
    public void update(PedidosEntity entity) {
        this.pedidosRepository.save(entity);
    }

    @Override
    public PedidosEntity save(PedidosEntity entity) {
        return this.pedidosRepository.save(entity);
    }

    @Override
    public void delete(PedidosEntity entity) {
        this.pedidosRepository.delete(entity);
    }

    @Override
    public void create(PedidosEntity entity) {
        this.pedidosRepository.save(entity);
    }
}