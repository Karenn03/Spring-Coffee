package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.DeliveryDTO;
import com.app.CoffeeTech.Entity.DeliveryEntity;
import com.app.CoffeeTech.Service.DeliveryService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DeliveryBusiness {

    @Autowired
    DeliveryService deliveryService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Deliveries
    public Page<DeliveryDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<DeliveryEntity> domicilioList = deliveryService.findAll(pageRequest);
            if (domicilioList.isEmpty()){
                return Page.empty();
            }
            return domicilioList.map(deliveryEntity -> modelMapper.map(deliveryEntity, DeliveryDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los domicilios." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Delivery by ID
    public DeliveryDTO findById(Long id){
        try {
            DeliveryEntity deliveryEntity = deliveryService.getById(id);
            if (deliveryEntity == null){
                throw new CustomException("Domicilio con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(deliveryEntity, DeliveryDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener el domicilio por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Delivery
    public void add(DeliveryDTO deliveryDto){
        try {
            DeliveryEntity deliveryEntity = modelMapper.map(deliveryDto, DeliveryEntity.class);
            deliveryService.save(deliveryEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando el domicilio." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Delivery
    public void update(Long id, DeliveryDTO deliveryDto) {
        try {
            DeliveryEntity existingDelivery = deliveryService.getById(id);
            if (existingDelivery == null) {
                throw new CustomException("Domicilio con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            existingDelivery.setDireccion(deliveryDto.getDireccion());
            existingDelivery.setEspecificaciones(deliveryDto.getEspecificaciones());
            deliveryService.save(existingDelivery);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el domicilio." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Delivery
    public void delete(Long idDomicilio) {
        try {
            DeliveryEntity deliveryEntity = deliveryService.getById(idDomicilio);
            if (deliveryEntity == null) {
                throw new CustomException("Domicilio con id " + idDomicilio + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            deliveryService.delete(deliveryEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el domicilio: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}