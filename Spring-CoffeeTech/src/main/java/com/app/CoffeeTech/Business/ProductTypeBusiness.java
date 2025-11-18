package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.ProductTypeDTO;
import com.app.CoffeeTech.Entity.ProductTypeEntity;
import com.app.CoffeeTech.Service.ProductTypeService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeBusiness {

    @Autowired
    ProductTypeService productTypeService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Deliveries
    public Page<ProductTypeDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<ProductTypeEntity> tipoProductoList = productTypeService.findAll(pageRequest);
            if (tipoProductoList.isEmpty()){
                return Page.empty();
            }
            return tipoProductoList.map(productTypeEntity -> modelMapper.map(productTypeEntity, ProductTypeDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los tipos de productos." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Product Type by ID
    public ProductTypeDTO findById(Long id){
        try {
            ProductTypeEntity productTypeEntity = productTypeService.getById(id);
            if (productTypeEntity == null){
                throw new CustomException("Tipo de producto con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(productTypeEntity, ProductTypeDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener el tipo de producto por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Product Type
    public void add(ProductTypeDTO productTypeDto){
        try {
            ProductTypeEntity productTypeEntity = modelMapper.map(productTypeDto, ProductTypeEntity.class);
            productTypeService.save(productTypeEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando el tipo de producto." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Product Type
    public void update(Long id, ProductTypeDTO productTypeDto) {
        try {
            ProductTypeEntity existingProductType = productTypeService.getById(id);
            if (existingProductType == null) {
                throw new CustomException("Tipo de producto con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            existingProductType.setNombreTipoProd(productTypeDto.getNombreTipoProd());
            productTypeService.save(existingProductType);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el tipo de producto." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Product Type
    public void delete(Long idTipoProducto) {
        try {
            ProductTypeEntity productTypeEntity = productTypeService.getById(idTipoProducto);
            if (productTypeEntity == null) {
                throw new CustomException("Tipo de producto con id " + idTipoProducto + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            productTypeService.delete(productTypeEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el tipo de producto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}