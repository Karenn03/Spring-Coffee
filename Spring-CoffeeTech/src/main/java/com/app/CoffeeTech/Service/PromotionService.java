package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.PromotionEntity;
import com.app.CoffeeTech.Repository.PromotionRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class PromotionService implements IDAO<PromotionEntity, Long> {

    @Autowired
    PromotionRepository promotionRepository;

    @Override
    public Page<PromotionEntity> findAll(PageRequest pageRequest) {
        return promotionRepository.findAll(pageRequest);
    }

    @Override
    public PromotionEntity getById(Long id) {
        Optional<PromotionEntity> optionalPromotions = promotionRepository.findById(id);
        return optionalPromotions.orElse(null);
    }

    @Override
    public void update(PromotionEntity entity) {
        this.promotionRepository.save(entity);
    }

    @Override
    public PromotionEntity save(PromotionEntity entity) {
        return this.promotionRepository.save(entity);
    }

    @Override
    public void delete(PromotionEntity entity) {
        this.promotionRepository.delete(entity);
    }

    @Override
    public void create(PromotionEntity entity) {
        this.promotionRepository.save(entity);
    }
}
