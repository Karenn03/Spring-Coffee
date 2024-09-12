package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PedidosDTO;
import com.app.CoffeeTech.Entity.PedidosEntity;
import com.app.CoffeeTech.Service.PedidosService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidosBusiness {

    @Autowired
    PedidosService pedidosService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<PedidosDTO> findAll(){
        try {
            List<PedidosEntity> pedidosList = pedidosService.findAll();
            if (pedidosList.isEmpty()){
                return new ArrayList<>();
            }
            List<PedidosDTO> pedidosDtoList = new ArrayList<>();
            pedidosList.forEach(PedidosEntity -> pedidosDtoList.add(modelMapper.map(PedidosEntity, PedidosDTO.class)));
            return pedidosDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los pedidos.");
        }
    }

    public PedidosDTO getById(Long id){
        try {
            PedidosEntity pedidosEntity = pedidosService.getById(id);
            if (pedidosEntity == null){
                throw new CustomException("Pedido con id " + id + " no encontrado.");
            }
            return modelMapper.map(pedidosEntity, PedidosDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener el pedido por id.");
        }
    }

    public void update(Long id, PedidosDTO pedidosDto) {
        try {
            PedidosEntity existingOrder = pedidosService.getById(id);
            if (existingOrder == null) {
                throw new CustomException("Pedido con id " + id + " no se encuentra.");
            }
            existingOrder.setTotal(pedidosDto.getTotal());
            pedidosService.save(existingOrder);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el pedido.");
        }
    }

    public void create(PedidosDTO pedidosDto){
        try {
            PedidosEntity pedidosEntity = modelMapper.map(pedidosDto, PedidosEntity.class);
            pedidosService.save(pedidosEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el pedido.");
        }
    }

    // Metodo para eliminar un pedido
    public void delete(Long idPedido) {
        try {
            PedidosEntity pedidosEntity = pedidosService.getById(idPedido);
            if (pedidosEntity == null) {
                throw new CustomException("Pedido con id " + idPedido + " no encontrado.");
            }
            pedidosService.delete(pedidosEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el pedido: " + e.getMessage());
        }
    }
}