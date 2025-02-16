package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.TipoProductoDTO;
import com.app.CoffeeTech.Entity.TipoProductoEntity;
import com.app.CoffeeTech.Service.TipoProductoService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TipoProductoBusiness {

    @Autowired
    TipoProductoService tipoProductoService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Deliveries
    public Page<TipoProductoDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<TipoProductoEntity> tipoProductoList = tipoProductoService.findAll(pageRequest);
            if (tipoProductoList.isEmpty()){
                return Page.empty();
            }
            return tipoProductoList.map(tipoProductoEntity -> modelMapper.map(tipoProductoEntity, TipoProductoDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los tipos de productos." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Product Type by ID
    public TipoProductoDTO findById(Long id){
        try {
            TipoProductoEntity tipoProductoEntity = tipoProductoService.getById(id);
            if (tipoProductoEntity == null){
                throw new CustomException("Tipo de producto con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(tipoProductoEntity, TipoProductoDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener el tipo de producto por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Product Type
    public void add(TipoProductoDTO tipoProductoDto){
        try {
            TipoProductoEntity tipoProductoEntity = modelMapper.map(tipoProductoDto, TipoProductoEntity.class);
            tipoProductoService.save(tipoProductoEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando el tipo de producto." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Product Type
    public void update(Long id, TipoProductoDTO tipoProductoDto) {
        try {
            TipoProductoEntity existingProductType = tipoProductoService.getById(id);
            if (existingProductType == null) {
                throw new CustomException("Tipo de producto con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            existingProductType.setNombreTipoProd(tipoProductoDto.getNombreTipoProd());
            tipoProductoService.save(existingProductType);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el tipo de producto." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Product Type
    public void delete(Long idTipoProducto) {
        try {
            TipoProductoEntity tipoProductoEntity = tipoProductoService.getById(idTipoProducto);
            if (tipoProductoEntity == null) {
                throw new CustomException("Tipo de producto con id " + idTipoProducto + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            tipoProductoService.delete(tipoProductoEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el tipo de producto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}