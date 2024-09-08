package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.TipoProductoEntity;
import com.app.CoffeeTech.Repository.TipoProductoRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class TipoProductoService implements IDAO<TipoProductoEntity, Long> {

    @Autowired
    TipoProductoRepository tipoProductoRepository;

    @Override
    public List<TipoProductoEntity> findAll() {
        return tipoProductoRepository.findAll();
    }

    @Override
    public TipoProductoEntity getById(Long id) {
        Optional<TipoProductoEntity> optionalProductTye = tipoProductoRepository.findById(id);
        return optionalProductTye.orElse(null);
    }

    @Override
    public void update(TipoProductoEntity entity) {
        this.tipoProductoRepository.save(entity);
    }

    @Override
    public TipoProductoEntity save(TipoProductoEntity entity) {
        return this.tipoProductoRepository.save(entity);
    }

    @Override
    public void delete(TipoProductoEntity entity) {
        this.tipoProductoRepository.delete(entity);
    }

    @Override
    public void create(TipoProductoEntity entity) {
        this.tipoProductoRepository.save(entity);
    }
}