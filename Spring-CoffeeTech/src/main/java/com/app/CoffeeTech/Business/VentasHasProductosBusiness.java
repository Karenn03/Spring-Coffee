package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.VentasHasProductosDTO;
import com.app.CoffeeTech.Entity.VentasHasProductosEntity;
import com.app.CoffeeTech.Service.VentasHasProductosService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VentasHasProductosBusiness {

    @Autowired
    VentasHasProductosService ventasHasProductosService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<VentasHasProductosDTO> findAll(){
        try {
            List<VentasHasProductosEntity> ventasHasProductosList = ventasHasProductosService.findAll();
            if (ventasHasProductosList.isEmpty()){
                return new ArrayList<>();
            }
            List<VentasHasProductosDTO> ventasHasProductosDtoList = new ArrayList<>();
            ventasHasProductosList.forEach(VentasHasProductosEntity -> ventasHasProductosDtoList.add(modelMapper.map(VentasHasProductosEntity, VentasHasProductosDTO.class)));
            return ventasHasProductosDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los 'ventas has productos'.");
        }
    }

    public VentasHasProductosDTO getById(Long id){
        try {
            VentasHasProductosEntity ventasHasProductosEntity = ventasHasProductosService.getById(id);
            if (ventasHasProductosEntity == null){
                throw new CustomException("'Ventas has productos' con id " + id + " no encontrado.");
            }
            return modelMapper.map(ventasHasProductosEntity, VentasHasProductosDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener el 'ventas has productos' por id.");
        }
    }

    public void update(Long id, VentasHasProductosDTO ventasHasProductosDto) {
        try {
            VentasHasProductosEntity existingSalesHasProducts = ventasHasProductosService.getById(id);
            if (existingSalesHasProducts == null) {
                throw new CustomException("'Ventas has productos' con id " + id + " no se encuentra.");
            }
            existingSalesHasProducts.setIdVentasHasProductos(ventasHasProductosDto.getIdVentasHasProductos());
            ventasHasProductosService.save(existingSalesHasProducts);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el 'ventas has productos'.");
        }
    }

    public void create(VentasHasProductosDTO ventasHasProductosDto){
        try {
            VentasHasProductosEntity domiclioEntity = modelMapper.map(ventasHasProductosDto, VentasHasProductosEntity.class);
            ventasHasProductosService.save(domiclioEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el 'ventas has productos'.");
        }
    }

    public void delete(Long idVentasHasProductos) {
        try {
            VentasHasProductosEntity ventasHasProductosEntity = ventasHasProductosService.getById(idVentasHasProductos);
            if (ventasHasProductosEntity == null) {
                throw new CustomException("'Ventas has productos' con id " + idVentasHasProductos + " no encontrado.");
            }
            ventasHasProductosService.delete(ventasHasProductosEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el 'ventas has productos': " + e.getMessage());
        }
    }
}