package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.CarritoComprasEntity;
import com.app.CoffeeTech.Repository.CarritoComprasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CarritoComprasService implements IDAO<CarritoComprasEntity, Long> {

    @Autowired
    CarritoComprasRepository CarritoComprasRepository;

    @Override
    public List<CarritoComprasEntity> findAll() {
        return List.of();
    }

    @Override
    public CarritoComprasEntity getById(Long aLong) {
        return null;
    }

    @Override
    public void update(CarritoComprasEntity entity) {
        this.CarritoComprasRepository.save(entity);
    }

    @Override
    public CarritoComprasEntity save(CarritoComprasEntity entity) {
        return this.CarritoComprasRepository.save(entity);
    }

    @Override
    public void delete(CarritoComprasEntity entity) {
        this.CarritoComprasRepository.delete(entity);
    }

    @Override
    public void create(CarritoComprasEntity entity) {
        this.CarritoComprasRepository.save(entity);
    }
}