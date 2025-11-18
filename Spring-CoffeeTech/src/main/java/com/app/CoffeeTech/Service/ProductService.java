package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ProductEntity;
import com.app.CoffeeTech.Repository.ProductRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class ProductService implements IDAO<ProductEntity, Long> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<ProductEntity> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public ProductEntity getById(Long id) {
        Optional<ProductEntity> optionalProducts = productRepository.findById(id);
        return optionalProducts.orElse(null);
    }

    @Override
    public void update(ProductEntity entity) {
        this.productRepository.save(entity);
    }

    @Override
    public ProductEntity save(ProductEntity entity) {
        return this.productRepository.save(entity);
    }

    @Override
    public void delete(ProductEntity entity) {
        this.productRepository.delete(entity);
    }

    @Override
    public void create(ProductEntity entity) {
        this.productRepository.save(entity);
    }
}