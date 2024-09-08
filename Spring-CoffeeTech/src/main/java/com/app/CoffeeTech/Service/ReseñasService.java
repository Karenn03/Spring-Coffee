package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ReseñasEntity;
import com.app.CoffeeTech.Repository.ReseñasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ReseñasService implements IDAO<ReseñasEntity, Long> {

    @Autowired
    ReseñasRepository reseñasRepository;

    @Override
    public List<ReseñasEntity> findAll() {
        return reseñasRepository.findAll();
    }

    @Override
    public ReseñasEntity getById(Long id) {
        Optional<ReseñasEntity> optionalReviews = reseñasRepository.findById(id);
        return optionalReviews.orElse(null);
    }

    @Override
    public void update(ReseñasEntity entity) {
        this.reseñasRepository.save(entity);
    }

    @Override
    public ReseñasEntity save(ReseñasEntity entity) {
        return this.reseñasRepository.save(entity);
    }

    @Override
    public void delete(ReseñasEntity entity) {
        this.reseñasRepository.delete(entity);
    }

    @Override
    public void create(ReseñasEntity entity) {
        this.reseñasRepository.save(entity);
    }
}