package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.VentasDTO;
import com.app.CoffeeTech.Entity.VentasEntity;
import com.app.CoffeeTech.Service.VentasService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class VentasBusiness {

    @Autowired
    VentasService ventasService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Sales
    public Page<VentasDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<VentasEntity> ventasList = ventasService.findAll(pageRequest);
            if (ventasList.isEmpty()){
                return Page.empty();
            }
            return ventasList.map(ventasEntity -> modelMapper.map(ventasEntity, VentasDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las ventas." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Sale by ID
    public VentasDTO findById(Long id){
        try {
            VentasEntity ventasEntity = ventasService.getById(id);
            if (ventasEntity == null){
                throw new CustomException("Venta con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(ventasEntity, VentasDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener la venta por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Sale
    public void add(VentasDTO ventasDto){
        try {
            VentasEntity ventasEntity = modelMapper.map(ventasDto, VentasEntity.class);
            ventasService.save(ventasEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando la venta." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Sale
    public void update(Long id, VentasDTO ventasDto) {
        try {
            VentasEntity existingSale = ventasService.getById(id);
            if (existingSale == null) {
                throw new CustomException("Venta con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            existingSale.setCantidad(ventasDto.getCantidad());
            ventasService.save(existingSale);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la venta." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Sale
    public void delete(Long idVentas) {
        try {
            VentasEntity ventasEntity = ventasService.getById(idVentas);
            if (ventasEntity == null) {
                throw new CustomException("Venta con id " + idVentas + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            ventasService.delete(ventasEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la venta: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}