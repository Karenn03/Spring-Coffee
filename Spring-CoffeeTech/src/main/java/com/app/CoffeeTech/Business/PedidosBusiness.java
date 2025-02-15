package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PedidosDTO;
import com.app.CoffeeTech.Entity.PedidosEntity;
import com.app.CoffeeTech.Service.PedidosService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PedidosBusiness {

    @Autowired
    PedidosService pedidosService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Orders
    public Page<PedidosDTO> findAll(Pageable pageable){
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<PedidosEntity> pedidosPage = pedidosService.findAll(pageRequest);
            if (pedidosPage.isEmpty()) {
                return Page.empty();
            }
            return pedidosPage.map(pedidosEntity -> modelMapper.map(pedidosEntity, PedidosDTO.class));
        } catch (Exception e) {
            throw new CustomException("Error al obtener todos los pedidos." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Order by ID
    public PedidosDTO findById(Long id) {
        try {
            PedidosEntity pedidosEntity = pedidosService.getById(id);
            if (pedidosEntity == null) {
                throw new CustomException("Pedido con id: " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(pedidosEntity, PedidosDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al obtener el pedido por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Order
    public void add(PedidosDTO pedidosDto) {
        try {
            PedidosEntity classType = modelMapper.map(pedidosDto, PedidosEntity.class);
            pedidosService.save(classType);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error creando el pedido: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Order
    public void update(Long id, PedidosDTO pedidosDTO) {
        try {
            PedidosEntity existingOrder = pedidosService.getById(id);
            if (existingOrder == null) {
                throw new CustomException("Pedido con id: " + id + " no se encuentra.", HttpStatus.NOT_FOUND);
            }
            existingOrder.setTotal(pedidosDTO.getTotal());
            pedidosService.save(existingOrder);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el pedido: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Order
    public void delete(Long idPedido) {
        try {
            PedidosEntity pedidosEntity = pedidosService.getById(idPedido);
            if (pedidosEntity == null) {
                throw new CustomException("Pedido con id " + idPedido + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            pedidosService.delete(pedidosEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el pedido: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}