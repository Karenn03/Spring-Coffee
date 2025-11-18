package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.TableDTO;
import com.app.CoffeeTech.Entity.TableEntity;
import com.app.CoffeeTech.Service.TableService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TableBusiness {

    @Autowired
    TableService tableService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Tables
    public Page<TableDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<TableEntity> mesasPage = tableService.findAll(pageRequest);
            if (mesasPage.isEmpty()){
                return Page.empty();
            }
            return mesasPage.map(tableEntity -> modelMapper.map(tableEntity, TableDTO.class));
        } catch (Exception e) {
            throw new CustomException("Error al obtener todas las mesas." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Table by ID
    public TableDTO findById(Long id){
        try {
            TableEntity tableEntity = tableService.getById(id);
            if (tableEntity == null){
                throw new CustomException("Mesa con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(tableEntity, TableDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al obtener la mesa por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Table
    public void add(TableDTO tableDto){
        try {
            TableEntity tableEntity = modelMapper.map(tableDto, TableEntity.class);
            tableService.save(tableEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error creando la mesa." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Table
    public void update(Long id, TableDTO tableDto) {
        try {
            TableEntity existingTable = tableService.getById(id);
            if (existingTable == null) {
                throw new CustomException("Mesa con id " + id + " no se encuentra.", HttpStatus.NOT_FOUND);
            }
            existingTable.setNumero(tableDto.getNumero());
            tableService.save(existingTable);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la mesa." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Toggle Table (activate or deactivate)
    public Boolean toggles(Long id) {
        try {
            TableEntity tableEntity = tableService.getById(id);
            if (tableEntity.getState().equals(Boolean.TRUE)) {
                tableEntity.setState(false);
                tableService.save(tableEntity);
                return false;
            } else {
                tableEntity.setState(true);
                tableService.save(tableEntity);
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
            TableEntity tableEntity = tableService.getById(idMesa);
            if (tableEntity == null) {
                throw new CustomException("Mesa con id " + idMesa + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            tableService.delete(tableEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la mesa: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}