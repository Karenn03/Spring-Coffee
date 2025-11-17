package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.MesasDTO;
import com.app.CoffeeTech.Entity.MesasEntity;
import com.app.CoffeeTech.Service.MesasService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MesasBusiness {

    @Autowired
    MesasService mesasService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Tables
    public Page<MesasDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<MesasEntity> mesasPage = mesasService.findAll(pageRequest);
            if (mesasPage.isEmpty()){
                return Page.empty();
            }
            return mesasPage.map(mesasEntity -> modelMapper.map(mesasEntity, MesasDTO.class));
        } catch (Exception e) {
            throw new CustomException("Error al obtener todas las mesas." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Table by ID
    public MesasDTO findById(Long id){
        try {
            MesasEntity mesasEntity = mesasService.getById(id);
            if (mesasEntity == null){
                throw new CustomException("Mesa con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(mesasEntity, MesasDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al obtener la mesa por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Table
    public void add(MesasDTO mesasDto){
        try {
            MesasEntity mesasEntity = modelMapper.map(mesasDto, MesasEntity.class);
            mesasService.save(mesasEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error creando la mesa." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Table
    public void update(Long id, MesasDTO mesasDto) {
        try {
            MesasEntity existingTable = mesasService.getById(id);
            if (existingTable == null) {
                throw new CustomException("Mesa con id " + id + " no se encuentra.", HttpStatus.NOT_FOUND);
            }
            existingTable.setNumero(mesasDto.getNumero());
            mesasService.save(existingTable);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la mesa." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Toggle Table (activate or deactivate)
    public Boolean toggles(Long id) {
        try {
            MesasEntity mesasEntity = mesasService.getById(id);
            if (mesasEntity.getState().equals(Boolean.TRUE)) {
                mesasEntity.setState(false);
                mesasService.save(mesasEntity);
                return false;
            } else {
                mesasEntity.setState(true);
                mesasService.save(mesasEntity);
                return true;
            }
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error alternando el estado de la mesa: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Table
    public void delete(Long idMesa) {
        try {
            MesasEntity mesasEntity = mesasService.getById(idMesa);
            if (mesasEntity == null) {
                throw new CustomException("Mesa con id " + idMesa + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            mesasService.delete(mesasEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la mesa: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}