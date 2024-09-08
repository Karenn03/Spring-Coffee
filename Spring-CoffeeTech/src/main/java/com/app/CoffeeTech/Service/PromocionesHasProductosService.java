package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.PromocionesHasProductosEntity;
import com.app.CoffeeTech.Repository.PromocionesHasProductosRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionesHasProductosService implements IDAO<PromocionesHasProductosEntity, Long> {

    @Autowired
    PromocionesHasProductosRepository promocionesHasProductosRepository;

    @Override
    public List<PromocionesHasProductosEntity> findAll() {
        return promocionesHasProductosRepository.findAll();
    }

    @Override
    public PromocionesHasProductosEntity getById(Long id) {
        Optional<PromocionesHasProductosEntity> optionalProducts = promocionesHasProductosRepository.findById(id);
        return optionalProducts.orElse(null);
    }

    @Override
    public void update(PromocionesHasProductosEntity entity) {
        this.promocionesHasProductosRepository.save(entity);
    }

    @Override
    public PromocionesHasProductosEntity save(PromocionesHasProductosEntity entity) {
        return this.promocionesHasProductosRepository.save(entity);
    }

    @Override
    public void delete(PromocionesHasProductosEntity entity) {
        this.promocionesHasProductosRepository.delete(entity);
    }

    @Override
    public void create(PromocionesHasProductosEntity entity) {
        this.promocionesHasProductosRepository.save(entity);
    }
}
