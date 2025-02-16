package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ReseñasEntity;
import com.app.CoffeeTech.Repository.ReseñasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class ReseñasService implements IDAO<ReseñasEntity, Long> {

    @Autowired
    ReseñasRepository reseñasRepository;

    @Override
    public Page<ReseñasEntity> findAll(PageRequest pageRequest) {
        return reseñasRepository.findAll(pageRequest);
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