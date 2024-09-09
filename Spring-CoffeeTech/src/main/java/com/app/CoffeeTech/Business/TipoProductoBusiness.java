package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.TipoProductoDTO;
import com.app.CoffeeTech.Entity.TipoProductoEntity;
import com.app.CoffeeTech.Service.TipoProductoService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TipoProductoBusiness {

    @Autowired
    TipoProductoService tipoProductoService;

    private final ModelMapper modelMapper = new ModelMapper();

    //Metodo para traer todos los tipos de producto
    public List<TipoProductoDTO> findAll(){
        try {
            List<TipoProductoEntity> tipoProductoList = tipoProductoService.findAll();
            if (tipoProductoList.isEmpty()){
                return new ArrayList<>();
            }
            List<TipoProductoDTO> tipoProductoDtoList = new ArrayList<>();
            tipoProductoList.forEach(TipoProductoEntity -> tipoProductoDtoList.add(modelMapper.map(TipoProductoEntity, TipoProductoDTO.class)));
            return tipoProductoDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todas los tipos de producto.");
        }
    }

    //Metodo para buscar por id
    public TipoProductoDTO getById(Long id){
        try {
            TipoProductoEntity tipoProductoEntity = tipoProductoService.getById(id);
            if (tipoProductoEntity == null){
                throw new CustomException("Tipo de producto con id " + id + " no encontrado.");
            }
            return modelMapper.map(tipoProductoEntity, TipoProductoDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener el tipo de producto por id.");
        }
    }

    // Método para actualizar un tipo de producto
    public void update(Long id, TipoProductoDTO tipoProductoDto) {
        try {
            TipoProductoEntity existingProductType = tipoProductoService.getById(id);
            if (existingProductType == null) {
                throw new CustomException("Tipo de producto con id " + id + " no se encuentra.");
            }
            existingProductType.setNombreTipoProd(tipoProductoDto.getNombreTipoProd());
            tipoProductoService.save(existingProductType);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el tipo de producto.");
        }
    }

    //Metodo para crear, guardar un nuevo tipo de producto
    public void create(TipoProductoDTO tipoProductoDto){
        try {
            Long IdTipoProducto = tipoProductoDto.getIdTipoProducto();
            TipoProductoEntity existingProductType = tipoProductoService.getById(IdTipoProducto);
            if (existingProductType != null) {
                throw new CustomException("El tipo de producto con el id " + IdTipoProducto + " ya existe.");
            }
            TipoProductoEntity tipoProductoEntity = modelMapper.map(tipoProductoDto, TipoProductoEntity.class);
            tipoProductoService.save(tipoProductoEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el tipo de producto.");
        }
    }

    // Metodo para eliminar un tipo de producto
    public void delete(Long idTipoProducto) {
        try {
            TipoProductoEntity tipoProductoEntity = tipoProductoService.getById(idTipoProducto);
            if (tipoProductoEntity == null) {
                throw new CustomException("Tipo de producto con id " + idTipoProducto + " no encontrado.");
            }
            tipoProductoService.delete(tipoProductoEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el tipo de producto: " + e.getMessage());
        }
    }
}