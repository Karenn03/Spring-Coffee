package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.CarritoComprasEntity;
import com.app.CoffeeTech.Repository.CarritoComprasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoComprasService implements IDAO<CarritoComprasEntity, Long> {

    @Autowired
    CarritoComprasRepository carritoComprasRepository;

    @Override
    public List<CarritoComprasEntity> findAll() {
        return carritoComprasRepository.findAll();
    }

    @Override
    public CarritoComprasEntity getById(Long id) {
        Optional<CarritoComprasEntity> optionalCartShopping = carritoComprasRepository.findById(id);
        return optionalCartShopping.orElse(null);
    }

    @Override
    public void update(CarritoComprasEntity entity) {
        this.carritoComprasRepository.save(entity);
    }

    @Override
    public CarritoComprasEntity save(CarritoComprasEntity entity) {
        return this.carritoComprasRepository.save(entity);
    }

    @Override
    public void delete(CarritoComprasEntity entity) {
        this.carritoComprasRepository.delete(entity);
    }

    @Override
    public void create(CarritoComprasEntity entity) {
        this.carritoComprasRepository.save(entity);
    }
}