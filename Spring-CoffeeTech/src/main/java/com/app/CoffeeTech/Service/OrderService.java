package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.OrderEntity;
import com.app.CoffeeTech.Repository.OrderRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class OrderService implements IDAO<OrderEntity, Long> {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Page<OrderEntity> findAll(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest);
    }

    @Override
    public OrderEntity getById(Long id) {
        Optional<OrderEntity> optionalOrders = orderRepository.findById(id);
        return optionalOrders.orElse(null);
    }

    @Override
    public void update(OrderEntity entity) {
        this.orderRepository.save(entity);
    }

    @Override
    public OrderEntity save(OrderEntity entity) {
        return this.orderRepository.save(entity);
    }

    @Override
    public void delete(OrderEntity entity) {
        this.orderRepository.delete(entity);
    }

    @Override
    public void create(OrderEntity entity) {
        this.orderRepository.save(entity);
    }
}