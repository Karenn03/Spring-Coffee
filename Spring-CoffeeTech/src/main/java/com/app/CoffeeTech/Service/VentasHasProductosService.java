package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.VentasHasProductosEntity;
import com.app.CoffeeTech.Repository.VentasHasProductosRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class VentasHasProductosService implements IDAO<VentasHasProductosEntity, Long> {

    @Autowired
    VentasHasProductosRepository ventasHasProductosRepository;

    @Override
    public List<VentasHasProductosEntity> findAll() {
        return ventasHasProductosRepository.findAll();
    }

    @Override
    public VentasHasProductosEntity getById(Long id) {
        Optional<VentasHasProductosEntity> optionalProductTye = ventasHasProductosRepository.findById(id);
        return optionalProductTye.orElse(null);
    }

    @Override
    public void update(VentasHasProductosEntity entity) {
        this.ventasHasProductosRepository.save(entity);
    }

    @Override
    public VentasHasProductosEntity save(VentasHasProductosEntity entity) {
        return this.ventasHasProductosRepository.save(entity);
    }

    @Override
    public void delete(VentasHasProductosEntity entity) {
        this.ventasHasProductosRepository.delete(entity);
    }

    @Override
    public void create(VentasHasProductosEntity entity) {
        this.ventasHasProductosRepository.save(entity);
    }
}