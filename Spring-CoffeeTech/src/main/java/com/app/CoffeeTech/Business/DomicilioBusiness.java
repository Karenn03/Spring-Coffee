package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.DomicilioDTO;
import com.app.CoffeeTech.Entity.DomicilioEntity;
import com.app.CoffeeTech.Service.DomicilioService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DomicilioBusiness {

    @Autowired
    DomicilioService domicilioService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<DomicilioDTO> findAll(){
        try {
            List<DomicilioEntity> domicilioList = domicilioService.findAll();
            if (domicilioList.isEmpty()){
                return new ArrayList<>();
            }
            List<DomicilioDTO> domicilioDtoList = new ArrayList<>();
            domicilioList.forEach(DomicilioEntity -> domicilioDtoList.add(modelMapper.map(DomicilioEntity, DomicilioDTO.class)));
            return domicilioDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los domicilios.");
        }
    }

    public DomicilioDTO getById(Long id){
        try {
            DomicilioEntity domicilioEntity = domicilioService.getById(id);
            if (domicilioEntity == null){
                throw new CustomException("Domiclio con id " + id + " no encontrado.");
            }
            return modelMapper.map(domicilioEntity, DomicilioDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener el domicilio por id.");
        }
    }

    public void update(Long id, DomicilioDTO domicilioDto) {
        try {
            DomicilioEntity existingDelivery = domicilioService.getById(id);
            if (existingDelivery == null) {
                throw new CustomException("Domicilio con id " + id + " no se encuentra.");
            }
            existingDelivery.setDireccion(domicilioDto.getDireccion());
            existingDelivery.setEspecificaciones(domicilioDto.getEspecificaciones());
            domicilioService.save(existingDelivery);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el domicilio.");
        }
    }

    public void create(DomicilioDTO domicilioDto){
        try {
            DomicilioEntity domiclioEntity = modelMapper.map(domicilioDto, DomicilioEntity.class);
            domicilioService.save(domiclioEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el domicilio.");
        }
    }

    public void delete(Long idDomicilio) {
        try {
            DomicilioEntity domicilioEntity = domicilioService.getById(idDomicilio);
            if (domicilioEntity == null) {
                throw new CustomException("Domicilio con id " + idDomicilio + " no encontrado.");
            }
            domicilioService.delete(domicilioEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el domicilio: " + e.getMessage());
        }
    }
}