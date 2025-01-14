package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.ProductosDTO;
import com.app.CoffeeTech.Entity.ProductosEntity;
import com.app.CoffeeTech.Service.ProductosService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductosBusiness {

    @Autowired
    ProductosService productosService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<ProductosDTO> findAll(){
        try {
            List<ProductosEntity> productosList = productosService.findAll();
            if (productosList.isEmpty()){
                return new ArrayList<>();
            }
            List<ProductosDTO> productosDtoList = new ArrayList<>();
            productosList.forEach(ProductosEntity -> productosDtoList.add(modelMapper.map(ProductosEntity, ProductosDTO.class)));
            return productosDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los productos.");
        }
    }

    public ProductosDTO getById(Long id){
        try {
            ProductosEntity productosEntity = productosService.getById(id);
            if (productosEntity == null){
                throw new CustomException("Producto con id " + id + " no encontrado.");
            }
            return modelMapper.map(productosEntity, ProductosDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener el producto por id.");
        }
    }

    public void update(Long id, ProductosDTO productosDto) {
        try {
            ProductosEntity existingProduct = productosService.getById(id);
            if (existingProduct == null) {
                throw new CustomException("Producto con id " + id + " no se encuentra.");
            }
            existingProduct.setNombre(productosDto.getNombre());
            productosService.save(existingProduct);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el producto.");
        }
    }

    public void create(ProductosDTO productosDto){
        try {
            ProductosEntity productosEntity = modelMapper.map(productosDto, ProductosEntity.class);
            productosService.save(productosEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el producto.");
        }
    }

    public void delete(Long idProducto) {
        try {
            ProductosEntity productosEntity = productosService.getById(idProducto);
            if (productosEntity == null) {
                throw new CustomException("Producto con id " + idProducto + " no encontrado.");
            }
            productosService.delete(productosEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el producto: " + e.getMessage());
        }
    }
}