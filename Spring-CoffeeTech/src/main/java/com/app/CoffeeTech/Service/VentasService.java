package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.VentasEntity;
import com.app.CoffeeTech.Repository.VentasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class VentasService implements IDAO<VentasEntity, Long> {

    @Autowired
    VentasRepository ventasRepository;

    @Override
    public List<VentasEntity> findAll() {
        return ventasRepository.findAll();
    }

    @Override
    public VentasEntity getById(Long id) {
        Optional<VentasEntity> optionalSales = ventasRepository.findById(id);
        return optionalSales.orElse(null);
    }

    @Override
    public void update(VentasEntity entity) {
        this.ventasRepository.save(entity);
    }

    @Override
    public VentasEntity save(VentasEntity entity) {
        return this.ventasRepository.save(entity);
    }

    @Override
    public void delete(VentasEntity entity) {
        this.ventasRepository.delete(entity);
    }

    @Override
    public void create(VentasEntity entity) {
        this.ventasRepository.save(entity);
    }
}