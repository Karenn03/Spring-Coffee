package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.ReseñasDTO;
import com.app.CoffeeTech.Entity.ReseñasEntity;
import com.app.CoffeeTech.Service.ReseñasService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ReseñasBusiness {

    @Autowired
    ReseñasService reseñasService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Reviews
    public Page<ReseñasDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<ReseñasEntity> reseñasList = reseñasService.findAll(pageRequest);
            if (reseñasList.isEmpty()){
                return Page.empty();
            }
            return reseñasList.map(reseñasEntity -> modelMapper.map(reseñasEntity, ReseñasDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las reseñas." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Review by ID
    public ReseñasDTO findById(Long id){
        try {
            ReseñasEntity reseñasEntity = reseñasService.getById(id);
            if (reseñasEntity == null){
                throw new CustomException("Reseña con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(reseñasEntity, ReseñasDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener la reseña por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Review
    public void add(ReseñasDTO reseñasDto){
        try {
            ReseñasEntity reseñasEntity = modelMapper.map(reseñasDto, ReseñasEntity.class);
            reseñasService.save(reseñasEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando la reseña." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Review
    public void update(Long id, ReseñasDTO reseñasDto) {
        try {
            ReseñasEntity existingReview = reseñasService.getById(id);
            if (existingReview == null) {
                throw new CustomException("Reseña con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            existingReview.setCalificacion(reseñasDto.getCalificacion());
            reseñasService.save(existingReview);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la reseña." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Review
    public void delete(Long idReseñas) {
        try {
            ReseñasEntity reseñasEntity = reseñasService.getById(idReseñas);
            if (reseñasEntity == null) {
                throw new CustomException("Reseñaa con id " + idReseñas + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            reseñasService.delete(reseñasEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la reseña: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
