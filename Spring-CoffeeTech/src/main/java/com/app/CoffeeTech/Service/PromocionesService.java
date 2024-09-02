package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.PromocionesEntity;
import com.app.CoffeeTech.Repository.PromocionesRespository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PromocionesService implements IDAO<PromocionesEntity, Long> {

    @Autowired
    PromocionesRespository promocionesRespository;

    @Override
    public List<PromocionesEntity> findAll() {
        return List.of();
    }

    @Override
    public PromocionesEntity getById(Long aLong) {
        return null;
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
