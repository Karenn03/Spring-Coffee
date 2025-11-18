package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.ReviewDTO;
import com.app.CoffeeTech.Entity.ReviewEntity;
import com.app.CoffeeTech.Service.ReviewService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ReviewBusiness {

    @Autowired
    ReviewService reviewService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Reviews
    public Page<ReviewDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<ReviewEntity> reseñasList = reviewService.findAll(pageRequest);
            if (reseñasList.isEmpty()){
                return Page.empty();
            }
            return reseñasList.map(reviewEntity -> modelMapper.map(reviewEntity, ReviewDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las reseñas." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Review by ID
    public ReviewDTO findById(Long id){
        try {
            ReviewEntity reviewEntity = reviewService.getById(id);
            if (reviewEntity == null){
                throw new CustomException("Reseña con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(reviewEntity, ReviewDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener la reseña por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Review
    public void add(ReviewDTO reviewDto){
        try {
            ReviewEntity reviewEntity = modelMapper.map(reviewDto, ReviewEntity.class);
            reviewService.save(reviewEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando la reseña." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Review
    public void update(Long id, ReviewDTO reviewDto) {
        try {
            ReviewEntity existingReview = reviewService.getById(id);
            if (existingReview == null) {
                throw new CustomException("Reseña con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            existingReview.setCalificacion(reviewDto.getCalificacion());
            reviewService.save(existingReview);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la reseña." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Review
    public void delete(Long idReseñas) {
        try {
            ReviewEntity reviewEntity = reviewService.getById(idReseñas);
            if (reviewEntity == null) {
                throw new CustomException("Reseñaa con id " + idReseñas + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            reviewService.delete(reviewEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la reseña: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
