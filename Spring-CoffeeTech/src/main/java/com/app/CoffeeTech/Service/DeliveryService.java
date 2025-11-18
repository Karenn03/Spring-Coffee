package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.DeliveryEntity;
import com.app.CoffeeTech.Repository.DeliveryRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class DeliveryService implements IDAO<DeliveryEntity, Long> {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    public Page<DeliveryEntity> findAll(PageRequest pageRequest) {
        return deliveryRepository.findAll(pageRequest);
    }

    @Override
    public DeliveryEntity getById(Long id) {
        Optional<DeliveryEntity> optionalAddress = deliveryRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    @Override
    public void update(DeliveryEntity entity) {
        this.deliveryRepository.save(entity);
    }

    @Override
    public DeliveryEntity save(DeliveryEntity entity) {
        return this.deliveryRepository.save(entity);
    }

    @Override
    public void delete(DeliveryEntity entity) {
        this.deliveryRepository.delete(entity);
    }

    @Override
    public void create(DeliveryEntity entity) {
        this.deliveryRepository.save(entity);
    }
}