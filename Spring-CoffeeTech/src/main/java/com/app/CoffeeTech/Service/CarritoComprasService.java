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
    public void create(CarritoComprasEntity entidad) {

    }

    @Override
    public CarritoComprasEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<CarritoComprasEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(CarritoComprasEntity entidad) {

    }

    @Override
    public void actualizar(CarritoComprasEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}