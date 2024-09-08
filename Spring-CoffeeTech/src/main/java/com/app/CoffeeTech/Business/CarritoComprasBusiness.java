package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Entity.CarritoComprasEntity;
import com.app.CoffeeTech.Repository.CarritoComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CarritoComprasBusiness {

    @Autowired
    CarritoComprasRepository carritoComprasRepository;

    public List<CarritoComprasEntity> getAllCarritoCompras() {
        return carritoComprasRepository.findAll();
    }

    public Optional<CarritoComprasEntity> getCarritoComprasById(Long id) {
        return carritoComprasRepository.findById(id);
    }

    public CarritoComprasEntity createCarritoCompras(CarritoComprasEntity carritoCompras) {
        return carritoComprasRepository.save(carritoCompras);
    }

    public CarritoComprasEntity updateCarritoCompras(Long id, CarritoComprasEntity carritoComprasDetails) {
        Optional<CarritoComprasEntity> carritoCompras = carritoComprasRepository.findById(id);
        if (carritoCompras.isPresent()) {
            CarritoComprasEntity existingCarritoCompras = carritoCompras.get();
            existingCarritoCompras.setFechaAgregado(carritoComprasDetails.getFechaAgregado());
            return carritoComprasRepository.save(existingCarritoCompras);
        } else {
            throw new RuntimeException("Carrito con id: " + id + " no encontrado");
        }
    }

    public void deleteCarritoCompras(Long id) {
        carritoComprasRepository.deleteById(id);
    }

}