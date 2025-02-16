package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.ProductosDTO;
import com.app.CoffeeTech.Entity.ProductosEntity;
import com.app.CoffeeTech.Service.ProductosService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductosBusiness {

    @Autowired
    ProductosService productosService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Products
    public Page<ProductosDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<ProductosEntity> productosList = productosService.findAll(pageRequest);
            if (productosList.isEmpty()){
                return Page.empty();
            }
            return productosList.map(productosEntity -> modelMapper.map(productosEntity, ProductosDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los productoss." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Product by ID
    public ProductosDTO findById(Long id){
        try {
            ProductosEntity productosEntity = productosService.getById(id);
            if (productosEntity == null){
                throw new CustomException("Producto con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(productosEntity, ProductosDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener el producto por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Product
    public void add(ProductosDTO productosDto){
        try {
            ProductosEntity productosEntity = modelMapper.map(productosDto, ProductosEntity.class);
            productosService.save(productosEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando el producto." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Product
    public void update(Long id, ProductosDTO productosDto) {
        try {
            ProductosEntity existingProduct = productosService.getById(id);
            if (existingProduct == null) {
                throw new CustomException("Producto con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            existingProduct.setNombre(productosDto.getNombre());
            productosService.save(existingProduct);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el producto." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Product
    public void delete(Long idProducto) {
        try {
            ProductosEntity productosEntity = productosService.getById(idProducto);
            if (productosEntity == null) {
                throw new CustomException("Producto con id " + idProducto + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            productosService.delete(productosEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el producto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}