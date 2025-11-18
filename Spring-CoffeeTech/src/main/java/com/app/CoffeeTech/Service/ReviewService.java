package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ReviewEntity;
import com.app.CoffeeTech.Repository.ReviewRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class ReviewService implements IDAO<ReviewEntity, Long> {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Page<ReviewEntity> findAll(PageRequest pageRequest) {
        return reviewRepository.findAll(pageRequest);
    }

    @Override
    public ReviewEntity getById(Long id) {
        Optional<ReviewEntity> optionalReviews = reviewRepository.findById(id);
        return optionalReviews.orElse(null);
    }

    @Override
    public void update(ReviewEntity entity) {
        this.reviewRepository.save(entity);
    }

    @Override
    public ReviewEntity save(ReviewEntity entity) {
        return this.reviewRepository.save(entity);
    }

    @Override
    public void delete(ReviewEntity entity) {
        this.reviewRepository.delete(entity);
    }

    @Override
    public void create(ReviewEntity entity) {
        this.reviewRepository.save(entity);
    }
}