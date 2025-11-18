package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.SaleDTO;
import com.app.CoffeeTech.Entity.SaleEntity;
import com.app.CoffeeTech.Service.SaleService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SaleBusiness {

    @Autowired
    SaleService saleService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Sales
    public Page<SaleDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<SaleEntity> ventasList = saleService.findAll(pageRequest);
            if (ventasList.isEmpty()){
                return Page.empty();
            }
            return ventasList.map(saleEntity -> modelMapper.map(saleEntity, SaleDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las ventas." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Sale by ID
    public SaleDTO findById(Long id){
        try {
            SaleEntity saleEntity = saleService.getById(id);
            if (saleEntity == null){
                throw new CustomException("Venta con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(saleEntity, SaleDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener la venta por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Sale
    public void add(SaleDTO saleDto){
        try {
            SaleEntity saleEntity = modelMapper.map(saleDto, SaleEntity.class);
            saleService.save(saleEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando la venta." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Sale
    public void update(Long id, SaleDTO saleDto) {
        try {
            SaleEntity existingSale = saleService.getById(id);
            if (existingSale == null) {
                throw new CustomException("Venta con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            existingSale.setCantidad(saleDto.getCantidad());
            saleService.save(existingSale);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la venta." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Sale
    public void delete(Long idVentas) {
        try {
            SaleEntity saleEntity = saleService.getById(idVentas);
            if (saleEntity == null) {
                throw new CustomException("Venta con id " + idVentas + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            saleService.delete(saleEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la venta: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}