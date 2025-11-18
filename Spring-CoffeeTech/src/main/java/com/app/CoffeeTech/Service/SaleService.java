package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.SaleEntity;
import com.app.CoffeeTech.Repository.SaleRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class SaleService implements IDAO<SaleEntity, Long> {

    @Autowired
    SaleRepository saleRepository;

    @Override
    public Page<SaleEntity> findAll(PageRequest pageRequest) {
        return saleRepository.findAll(pageRequest);
    }

    @Override
    public SaleEntity getById(Long id) {
        Optional<SaleEntity> optionalSales = saleRepository.findById(id);
        return optionalSales.orElse(null);
    }

    @Override
    public void update(SaleEntity entity) {
        this.saleRepository.save(entity);
    }

    @Override
    public SaleEntity save(SaleEntity entity) {
        return this.saleRepository.save(entity);
    }

    @Override
    public void delete(SaleEntity entity) {
        this.saleRepository.delete(entity);
    }

    @Override
    public void create(SaleEntity entity) {
        this.saleRepository.save(entity);
    }
}