package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.DomicilioDTO;
import com.app.CoffeeTech.Entity.DomicilioEntity;
import com.app.CoffeeTech.Service.DomicilioService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DomicilioBusiness {

    @Autowired
    DomicilioService domicilioService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Deliveries
    public Page<DomicilioDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<DomicilioEntity> domicilioList = domicilioService.findAll(pageRequest);
            if (domicilioList.isEmpty()){
                return Page.empty();
            }
            return domicilioList.map(domicilioEntity -> modelMapper.map(domicilioEntity, DomicilioDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los domicilios." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Delivery by ID
    public DomicilioDTO findById(Long id){
        try {
            DomicilioEntity domicilioEntity = domicilioService.getById(id);
            if (domicilioEntity == null){
                throw new CustomException("Domicilio con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(domicilioEntity, DomicilioDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener el domicilio por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Delivery
    public void add(DomicilioDTO domicilioDto){
        try {
            DomicilioEntity domicilioEntity = modelMapper.map(domicilioDto, DomicilioEntity.class);
            domicilioService.save(domicilioEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando el domicilio." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Delivery
    public void update(Long id, DomicilioDTO domicilioDto) {
        try {
            DomicilioEntity existingDelivery = domicilioService.getById(id);
            if (existingDelivery == null) {
                throw new CustomException("Domicilio con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            existingDelivery.setDireccion(domicilioDto.getDireccion());
            existingDelivery.setEspecificaciones(domicilioDto.getEspecificaciones());
            domicilioService.save(existingDelivery);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el domicilio." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Delivery
    public void delete(Long idDomicilio) {
        try {
            DomicilioEntity domicilioEntity = domicilioService.getById(idDomicilio);
            if (domicilioEntity == null) {
                throw new CustomException("Domicilio con id " + idDomicilio + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            domicilioService.delete(domicilioEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el domicilio: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}