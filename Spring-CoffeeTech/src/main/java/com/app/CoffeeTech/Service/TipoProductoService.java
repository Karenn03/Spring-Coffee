package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.TipoProductoEntity;
import com.app.CoffeeTech.Repository.TipoProductoRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TipoProductoService implements IDAO<TipoProductoEntity, Long> {

    @Autowired
    TipoProductoRepository tipoProductoRepository;

    @Override
    public void create(TipoProductoEntity entidad) {

    }

    @Override
    public TipoProductoEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<TipoProductoEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(TipoProductoEntity entidad) {

    }

    @Override
    public void actualizar(TipoProductoEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}