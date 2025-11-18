package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PromotionDTO;
import com.app.CoffeeTech.Entity.PromotionEntity;
import com.app.CoffeeTech.Service.PromotionService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PromotionBusiness {

    @Autowired
    PromotionService promotionService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Deliveries
    public Page<PromotionDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<PromotionEntity> promocionesList = promotionService.findAll(pageRequest);
            if (promocionesList.isEmpty()){
                return Page.empty();
            }
            return promocionesList.map(promocionesEntity -> modelMapper.map(promocionesEntity, PromotionDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las promociones." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Promotions by ID
    public PromotionDTO findById(Long id){
        try {
            PromotionEntity promotionEntity = promotionService.getById(id);
            if (promotionEntity == null){
                throw new CustomException("Promoción con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(promotionEntity, PromotionDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener la promoción por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Promotions
    public void add(PromotionDTO promotionDto){
        try {
            PromotionEntity promotionEntity = modelMapper.map(promotionDto, PromotionEntity.class);
            promotionService.save(promotionEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando la promoción." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Promotions
    public void update(Long id, PromotionDTO promotionDto) {
        try {
            PromotionEntity existingPromotion = promotionService.getById(id);
            if (existingPromotion == null) {
                throw new CustomException("Promoción con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            existingPromotion.setNombrePromocion(promotionDto.getNombrePromocion());
            promotionService.save(existingPromotion);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la promoción." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Promotions
    public void delete(Long idPromociones) {
        try {
            PromotionEntity promotionEntity = promotionService.getById(idPromociones);
            if (promotionEntity == null) {
                throw new CustomException("Promoción con id " + idPromociones + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            promotionService.delete(promotionEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la promoción: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}