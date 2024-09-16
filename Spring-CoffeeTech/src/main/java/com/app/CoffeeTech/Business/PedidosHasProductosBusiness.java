package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PedidosHasProductosDTO;
import com.app.CoffeeTech.Entity.PedidosHasProductosEntity;
import com.app.CoffeeTech.Service.PedidosHasProductosService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidosHasProductosBusiness {

    @Autowired
    PedidosHasProductosService pedidosHasProductosService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<PedidosHasProductosDTO> findAll(){
        try {
            List<PedidosHasProductosEntity> pedidosHasProductosList = pedidosHasProductosService.findAll();
            if (pedidosHasProductosList.isEmpty()){
                return new ArrayList<>();
            }
            List<PedidosHasProductosDTO> pedidosHasProductosDtoList = new ArrayList<>();
            pedidosHasProductosList.forEach(PedidosHasProductosEntity -> pedidosHasProductosDtoList.add(modelMapper.map(PedidosHasProductosEntity, PedidosHasProductosDTO.class)));
            return pedidosHasProductosDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los 'pedidos has productos'.");
        }
    }

    public PedidosHasProductosDTO getById(Long id){
        try {
            PedidosHasProductosEntity pedidosHasProductosEntity = pedidosHasProductosService.getById(id);
            if (pedidosHasProductosEntity == null){
                throw new CustomException("'Pedidos has productos' con id " + id + " no encontrado.");
            }
            return modelMapper.map(pedidosHasProductosEntity, PedidosHasProductosDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener el 'pedidos has productos' por id.");
        }
    }

    public void update(Long id, PedidosHasProductosDTO pedidosHasProductosDto) {
        try {
            PedidosHasProductosEntity existingOrdersHasProducts = pedidosHasProductosService.getById(id);
            if (existingOrdersHasProducts == null) {
                throw new CustomException("'Pedidos has productos' con id " + id + " no se encuentra.");
            }
            existingOrdersHasProducts.setIdPedidosHasProductos(pedidosHasProductosDto.getIdPedidosHasProductos());
            pedidosHasProductosService.save(existingOrdersHasProducts);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el 'pedidos has productos'.");
        }
    }

    public void create(PedidosHasProductosDTO pedidosHasProductosDto){
        try {
            PedidosHasProductosEntity pedidosHasProductosDtoEntity = modelMapper.map(pedidosHasProductosDto, PedidosHasProductosEntity.class);
            pedidosHasProductosService.save(pedidosHasProductosDtoEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el 'pedidos has productos'.");
        }
    }

    public void delete(Long idPedidosHasProductos) {
        try {
            PedidosHasProductosEntity pedidosHasProductosEntity = pedidosHasProductosService.getById(idPedidosHasProductos);
            if (pedidosHasProductosEntity == null) {
                throw new CustomException("'Pedidos has productos' con id " + idPedidosHasProductos + " no encontrado.");
            }
            pedidosHasProductosService.delete(pedidosHasProductosEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el 'pedidos has productos': " + e.getMessage());
        }
    }
}