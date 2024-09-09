package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.VentasDTO;
import com.app.CoffeeTech.Entity.VentasEntity;
import com.app.CoffeeTech.Service.VentasService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VentasBusiness {

    @Autowired
    VentasService ventasService;

    private final ModelMapper modelMapper = new ModelMapper();

    //Metodo para traer todas las ventas
    public List<VentasDTO> findAll(){
        try {
            List<VentasEntity> ventasList = ventasService.findAll();
            if (ventasList.isEmpty()){
                return new ArrayList<>();
            }
            List<VentasDTO> ventasDtoList = new ArrayList<>();
            ventasList.forEach(VentasEntity -> ventasDtoList.add(modelMapper.map(VentasEntity, VentasDTO.class)));
            return ventasDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las ventas.");
        }
    }

    //Metodo para buscar por id
    public VentasDTO getById(Long id){
        try {
            VentasEntity ventasEntity = ventasService.getById(id);
            if (ventasEntity == null){
                throw new CustomException("Venta con id " + id + " no encontrada.");
            }
            return modelMapper.map(ventasEntity, VentasDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener la venta por id.");
        }
    }

    // Método para actualizar una venta
    public void update(Long id, VentasDTO ventasDto) {
        try {
            VentasEntity existingSale = ventasService.getById(id);
            if (existingSale == null) {
                throw new CustomException("Venta con id " + id + " no se encuentra.");
            }
            existingSale.setCantidad(ventasDto.getCantidad());
            ventasService.save(existingSale);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la venta.");
        }
    }

    //Metodo para crear, guardar una nueva venta
    public void create(VentasDTO ventasDto){
        try {
            Long IdVentas = ventasDto.getIdVentas();
            VentasEntity existingSale = ventasService.getById(IdVentas);
            if (existingSale != null) {
                throw new CustomException("La venta con el id " + IdVentas + " ya existe.");
            }
            VentasEntity ventasEntity = modelMapper.map(ventasDto, VentasEntity.class);
            ventasService.save(ventasEntity);
        } catch (Exception e){
            throw new CustomException("Error creando la venta.");
        }
    }

    // Metodo para eliminar una venta
    public void delete(Long idVentas) {
        try {
            VentasEntity ventasEntity = ventasService.getById(idVentas);
            if (ventasEntity == null) {
                throw new CustomException("Venta con id " + idVentas + " no encontrada.");
            }
            ventasService.delete(ventasEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando la venta: " + e.getMessage());
        }
    }
}