package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.PromocionesEntity;
import com.app.CoffeeTech.Repository.PromocionesRespository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class PromocionesService implements IDAO<PromocionesEntity, Long> {

    @Autowired
    PromocionesRespository promocionesRespository;

    @Override
    public Page<PromocionesEntity> findAll(PageRequest pageRequest) {
        return promocionesRespository.findAll(pageRequest);
    }

    @Override
    public PromocionesEntity getById(Long id) {
        Optional<PromocionesEntity> optionalPromotions = promocionesRespository.findById(id);
        return optionalPromotions.orElse(null);
    }

    @Override
    public void update(PromocionesEntity entity) {
        this.promocionesRespository.save(entity);
    }

    @Override
    public PromocionesEntity save(PromocionesEntity entity) {
        return this.promocionesRespository.save(entity);
    }

    @Override
    public void delete(PromocionesEntity entity) {
        this.promocionesRespository.delete(entity);
    }

    @Override
    public void create(PromocionesEntity entity) {
        this.promocionesRespository.save(entity);
    }
}
