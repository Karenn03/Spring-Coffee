package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.DomicilioEntity;
import com.app.CoffeeTech.Repository.DomicilioRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IDAO<DomicilioEntity, Long> {

    @Autowired
    DomicilioRepository domicilioRepository;

    @Override
    public Page<DomicilioEntity> findAll(PageRequest pageRequest) {
        return domicilioRepository.findAll(pageRequest);
    }

    @Override
    public DomicilioEntity getById(Long id) {
        Optional<DomicilioEntity> optionalAddress = domicilioRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    @Override
    public void update(DomicilioEntity entity) {
        this.domicilioRepository.save(entity);
    }

    @Override
    public DomicilioEntity save(DomicilioEntity entity) {
        return this.domicilioRepository.save(entity);
    }

    @Override
    public void delete(DomicilioEntity entity) {
        this.domicilioRepository.delete(entity);
    }

    @Override
    public void create(DomicilioEntity entity) {
        this.domicilioRepository.save(entity);
    }
}