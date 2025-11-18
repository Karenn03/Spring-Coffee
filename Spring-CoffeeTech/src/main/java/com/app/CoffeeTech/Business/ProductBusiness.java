package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.ProductDTO;
import com.app.CoffeeTech.Entity.ProductEntity;
import com.app.CoffeeTech.Service.ProductService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductBusiness {

    @Autowired
    ProductService productService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Products
    public Page<ProductDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<ProductEntity> productosList = productService.findAll(pageRequest);
            if (productosList.isEmpty()){
                return Page.empty();
            }
            return productosList.map(productosEntity -> modelMapper.map(productosEntity, ProductDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los productoss." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Product by ID
    public ProductDTO findById(Long id){
        try {
            ProductEntity productEntity = productService.getById(id);
            if (productEntity == null){
                throw new CustomException("Producto con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(productEntity, ProductDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener el producto por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Product
    public void add(ProductDTO productDto){
        try {
            ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
            productService.save(productEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando el producto." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Product
    public void update(Long id, ProductDTO productDto) {
        try {
            ProductEntity existingProduct = productService.getById(id);
            if (existingProduct == null) {
                throw new CustomException("Producto con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            existingProduct.setNombre(productDto.getNombre());
            productService.save(existingProduct);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el producto." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Product
    public void delete(Long idProducto) {
        try {
            ProductEntity productEntity = productService.getById(idProducto);
            if (productEntity == null) {
                throw new CustomException("Producto con id " + idProducto + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            productService.delete(productEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el producto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}