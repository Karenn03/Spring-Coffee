package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ProductosEntity;
import com.app.CoffeeTech.Repository.ProductosRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService implements IDAO<ProductosEntity, Long> {

    @Autowired
    ProductosRepository productosRepository;

    @Override
    public List<ProductosEntity> findAll() {
        return productosRepository.findAll();
    }

    @Override
    public ProductosEntity getById(Long id) {
        Optional<ProductosEntity> optionalProducts = productosRepository.findById(id);
        return optionalProducts.orElse(null);
    }

    @Override
    public void update(ProductosEntity entity) {
        this.productosRepository.save(entity);
    }

    @Override
    public ProductosEntity save(ProductosEntity entity) {
        return this.productosRepository.save(entity);
    }

    @Override
    public void delete(ProductosEntity entity) {
        this.productosRepository.delete(entity);
    }

    @Override
    public void create(ProductosEntity entity) {
        this.productosRepository.save(entity);
    }
}