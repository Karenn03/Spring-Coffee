package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.MesasEntity;
import com.app.CoffeeTech.Repository.MesasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class MesasService implements IDAO<MesasEntity, Long> {

    @Autowired
    MesasRepository mesasRepository;

    @Override
    public List<MesasEntity> findAll() {
        return mesasRepository.findAll();
    }

    @Override
    public MesasEntity getById(Long id) {
        Optional<MesasEntity> optionalTable = mesasRepository.findById(id);
        return optionalTable.orElse(null);
    }

    @Override
    public void update(MesasEntity entity) {
        this.mesasRepository.save(entity);
    }

    @Override
    public MesasEntity save(MesasEntity entity) {
        return this.mesasRepository.save(entity);
    }

    @Override
    public void delete(MesasEntity entity) {
        this.mesasRepository.delete(entity);
    }

    @Override
    public void create(MesasEntity entity) {
        this.mesasRepository.save(entity);
    }
}