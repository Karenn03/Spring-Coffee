package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ProductosEntity;
import com.app.CoffeeTech.Repository.ProductosRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductosService implements IDAO<ProductosEntity, Long> {

    @Autowired
    ProductosRepository productosRepository;

    @Override
    public void create(ProductosEntity entidad) {

    }

    @Override
    public ProductosEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<ProductosEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(ProductosEntity entidad) {

    }

    @Override
    public void actualizar(ProductosEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}