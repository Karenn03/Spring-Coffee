package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.TipoProductoDTO;
import com.app.CoffeeTech.Entity.TipoProductoEntity;
import com.app.CoffeeTech.Service.TipoProductoService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
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

    public void create(TipoProductoDTO tipoProductoDto){
        try {
            TipoProductoEntity tipoProductoEntity = modelMapper.map(tipoProductoDto, TipoProductoEntity.class);
            tipoProductoService.save(tipoProductoEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el tipo de producto.");
        }
    }

    public void delete(Long idTipoProducto) {
        try {
            TipoProductoEntity tipoProductoEntity = tipoProductoService.getById(idTipoProducto);
            if (tipoProductoEntity == null) {
                throw new CustomException("Tipo de producto con id " + idTipoProducto + " no encontrado.");
            }
            tipoProductoService.delete(tipoProductoEntity);
        } catch (Exception e) {
            throw new CustomException("Error creando el tipo de producto.");
        }
    }
}