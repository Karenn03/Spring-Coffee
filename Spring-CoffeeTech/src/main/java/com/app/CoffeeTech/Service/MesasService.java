package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.MesasEntity;
import com.app.CoffeeTech.Repository.MesasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class MesasService implements IDAO<MesasEntity, Long> {

    @Autowired
    MesasRepository mesasRepository;

    @Override
    public Page<MesasEntity> findAll(PageRequest pageRequest) {
        return mesasRepository.findAll(pageRequest);
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