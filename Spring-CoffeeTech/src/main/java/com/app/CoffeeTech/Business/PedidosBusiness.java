package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Entity.PedidosEntity;
import com.app.CoffeeTech.Repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidosBusiness {

    @Autowired
    PedidosRepository pedidosRepository;

    public List<PedidosEntity> getAllPedidos() {
        return pedidosRepository.findAll();
    }

    public Optional<PedidosEntity> getPedidosById(Long id) {
        return pedidosRepository.findById(id);
    }

    public PedidosEntity createPedidos(PedidosEntity Pedidos) {
        return pedidosRepository.save(Pedidos);
    }

    public PedidosEntity updatePedidos(Long id, PedidosEntity pedidosDetails) {
        Optional<PedidosEntity> Pedidos = pedidosRepository.findById(id);
        if (Pedidos.isPresent()) {
            PedidosEntity existingPedidos = Pedidos.get();
            existingPedidos.setFechaPedido(pedidosDetails.getFechaPedido());
            existingPedidos.setTotal(pedidosDetails.getTotal());
            return pedidosRepository.save(existingPedidos);
        } else {
            throw new RuntimeException("Pedido con id: " + id + " no encontrado");
        }
    }

    public void deletePedidos(Long id) {
        pedidosRepository.deleteById(id);
    }

}