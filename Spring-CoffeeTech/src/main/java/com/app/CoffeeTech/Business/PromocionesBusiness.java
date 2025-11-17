package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PromocionesDTO;
import com.app.CoffeeTech.Entity.PromocionesEntity;
import com.app.CoffeeTech.Service.PromocionesService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PromocionesBusiness {

    @Autowired
    PromocionesService promocionesService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Deliveries
    public Page<PromocionesDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<PromocionesEntity> promocionesList = promocionesService.findAll(pageRequest);
            if (promocionesList.isEmpty()){
                return Page.empty();
            }
            return promocionesList.map(promocionesEntity -> modelMapper.map(promocionesEntity, PromocionesDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las promociones." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Promotions by ID
    public PromocionesDTO findById(Long id){
        try {
            PromocionesEntity promocionesEntity = promocionesService.getById(id);
            if (promocionesEntity == null){
                throw new CustomException("Promoción con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(promocionesEntity, PromocionesDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener la promoción por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Promotions
    public void add(PromocionesDTO promocionesDto){
        try {
            PromocionesEntity promocionesEntity = modelMapper.map(promocionesDto, PromocionesEntity.class);
            promocionesService.save(promocionesEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando la promoción." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Promotions
    public void update(Long id, PromocionesDTO promocionesDto) {
        try {
            PromocionesEntity existingPromotion = promocionesService.getById(id);
            if (existingPromotion == null) {
                throw new CustomException("Promoción con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            existingPromotion.setNombrePromocion(promocionesDto.getNombrePromocion());
            promocionesService.save(existingPromotion);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la promoción." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Promotions
    public void delete(Long idPromociones) {
        try {
            PromocionesEntity promocionesEntity = promocionesService.getById(idPromociones);
            if (promocionesEntity == null) {
                throw new CustomException("Promoción con id " + idPromociones + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            promocionesService.delete(promocionesEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la promoción: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}