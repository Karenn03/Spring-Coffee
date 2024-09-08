package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ProductosHasCarritoComprasEntity;
import com.app.CoffeeTech.Repository.ProductosHasCarritoComprasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosHasCarritoComprasService implements IDAO<ProductosHasCarritoComprasEntity, Long> {

    @Autowired
    ProductosHasCarritoComprasRepository productosHasCarritoComprasRepository;

    @Override
    public List<ProductosHasCarritoComprasEntity> findAll() {
        return productosHasCarritoComprasRepository.findAll();
    }

    @Override
    public ProductosHasCarritoComprasEntity getById(Long id) {
        Optional<ProductosHasCarritoComprasEntity> optionalOrders = productosHasCarritoComprasRepository.findById(id);
        return optionalOrders.orElse(null);
    }

    @Override
    public void update(ProductosHasCarritoComprasEntity entity) {
        this.productosHasCarritoComprasRepository.save(entity);
    }

    @Override
    public ProductosHasCarritoComprasEntity save(ProductosHasCarritoComprasEntity entity) {
        return this.productosHasCarritoComprasRepository.save(entity);
    }

    @Override
    public void delete(ProductosHasCarritoComprasEntity entity) {
        this.productosHasCarritoComprasRepository.delete(entity);
    }

    @Override
    public void create(ProductosHasCarritoComprasEntity entity) {
        this.productosHasCarritoComprasRepository.save(entity);
    }
}