package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.ProductosHasCarritoComprasEntity;
import com.app.CoffeeTech.Repository.ProductosHasCarritoComprasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductosHasCarritoComprasService implements IDAO<ProductosHasCarritoComprasEntity, Long> {

    @Autowired
    ProductosHasCarritoComprasRepository productosHasCarritoComprasRepository;

    @Override
    public void create(ProductosHasCarritoComprasEntity entidad) {

    }

    @Override
    public ProductosHasCarritoComprasEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<ProductosHasCarritoComprasEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(ProductosHasCarritoComprasEntity entidad) {

    }

    @Override
    public void actualizar(ProductosHasCarritoComprasEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}
