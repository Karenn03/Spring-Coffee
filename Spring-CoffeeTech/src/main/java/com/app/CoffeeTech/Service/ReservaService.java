package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ReservaEntity;
import com.app.CoffeeTech.Repository.ReservaRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ReservaService implements IDAO<ReservaEntity, Long> {

    @Autowired
    ReservaRepository reservaRepository;

    @Override
    public List<ReservaEntity> findAll() {
        return List.of();
    }

    @Override
    public ReservaEntity getById(Long aLong) {
        return null;
    }

    @Override
    public void update(ReservaEntity entity) {
        this.reservaRepository.save(entity);
    }

    @Override
    public ReservaEntity save(ReservaEntity entity) {
        return this.reservaRepository.save(entity);
    }

    @Override
    public void delete(ReservaEntity entity) {
        this.reservaRepository.delete(entity);
    }

    @Override
    public void create(ReservaEntity entity) {
        this.reservaRepository.save(entity);
    }
}
