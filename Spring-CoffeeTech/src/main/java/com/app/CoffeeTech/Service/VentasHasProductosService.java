package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.VentasHasProductosEntity;
import com.app.CoffeeTech.Repository.VentasHasProductosRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class VentasHasProductosService implements IDAO<VentasHasProductosEntity, Long> {

    @Autowired
    VentasHasProductosRepository ventasHasProductosRepository;

    @Override
    public void create(VentasHasProductosEntity entidad) {

    }

    @Override
    public VentasHasProductosEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<VentasHasProductosEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(VentasHasProductosEntity entidad) {

    }

    @Override
    public void actualizar(VentasHasProductosEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}