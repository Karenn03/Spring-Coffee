package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ProductTypeEntity;
import com.app.CoffeeTech.Repository.ProductTypeRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class ProductTypeService implements IDAO<ProductTypeEntity, Long> {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public Page<ProductTypeEntity> findAll(PageRequest pageRequest) {
        return productTypeRepository.findAll(pageRequest);
    }

    @Override
    public ProductTypeEntity getById(Long id) {
        Optional<ProductTypeEntity> optionalProductTye = productTypeRepository.findById(id);
        return optionalProductTye.orElse(null);
    }

    @Override
    public void update(ProductTypeEntity entity) {
        this.productTypeRepository.save(entity);
    }

    @Override
    public ProductTypeEntity save(ProductTypeEntity entity) {
        return this.productTypeRepository.save(entity);
    }

    @Override
    public void delete(ProductTypeEntity entity) {
        this.productTypeRepository.delete(entity);
    }

    @Override
    public void create(ProductTypeEntity entity) {
        this.productTypeRepository.save(entity);
    }
}