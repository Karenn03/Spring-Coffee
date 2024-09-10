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

    //Metodo para traer todos los domicilios
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

    //Metodo para buscar por id
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

    // Método para actualizar un domicilio
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

    //Metodo para crear, guardar un nuevo domicilio
    public void create(DomicilioDTO domicilioDto){
        try {
            Long IdDomicilio = domicilioDto.getIdDomicilio();
            DomicilioEntity existingDelivery = domicilioService.getById(IdDomicilio);
            if (existingDelivery != null) {
                throw new CustomException("El domicilio con el id " + IdDomicilio + " ya existe.");
            }
            DomicilioEntity domiclioEntity = modelMapper.map(domicilioDto, DomicilioEntity.class);
            domicilioService.save(domiclioEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el domicilio.");
        }
    }

    // Metodo para eliminar un domicilio
    public void delete(Long idDomicilio) {
        try {
            DomicilioEntity domiclioEntity = domicilioService.getById(idDomicilio);
            if (domiclioEntity == null) {
                throw new CustomException("Domicilio con id " + idDomicilio + " no encontrado.");
            }
            domicilioService.delete(domiclioEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el domicilio: " + e.getMessage());
        }
    }
}