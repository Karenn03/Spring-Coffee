package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.OrderDTO;
import com.app.CoffeeTech.Entity.OrderEntity;
import com.app.CoffeeTech.Service.OrderService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderBusiness {

    @Autowired
    OrderService orderService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Orders
    public Page<OrderDTO> findAll(Pageable pageable){
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<OrderEntity> pedidosPage = orderService.findAll(pageRequest);
            if (pedidosPage.isEmpty()) {
                return Page.empty();
            }
            return pedidosPage.map(pedidosEntity -> modelMapper.map(pedidosEntity, OrderDTO.class));
        } catch (Exception e) {
            throw new CustomException("Error al obtener todos los pedidos." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Order by ID
    public OrderDTO findById(Long id) {
        try {
            OrderEntity orderEntity = orderService.getById(id);
            if (orderEntity == null) {
                throw new CustomException("Pedido con id: " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(orderEntity, OrderDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al obtener el pedido por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Order
    public void add(OrderDTO orderDto) {
        try {
            OrderEntity classType = modelMapper.map(orderDto, OrderEntity.class);
            orderService.save(classType);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error creando el pedido: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Order
    public void update(Long id, OrderDTO orderDTO) {
        try {
            OrderEntity existingOrder = orderService.getById(id);
            if (existingOrder == null) {
                throw new CustomException("Pedido con id: " + id + " no se encuentra.", HttpStatus.NOT_FOUND);
            }
            existingOrder.setTotal(orderDTO.getTotal());
            orderService.save(existingOrder);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el pedido: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Order
    public void delete(Long idPedido) {
        try {
            OrderEntity orderEntity = orderService.getById(idPedido);
            if (orderEntity == null) {
                throw new CustomException("Pedido con id " + idPedido + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            orderService.delete(orderEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el pedido: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}