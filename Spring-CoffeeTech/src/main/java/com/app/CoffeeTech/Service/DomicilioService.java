package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.DomicilioEntity;
import com.app.CoffeeTech.Repository.DomicilioRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DomicilioService implements IDAO<DomicilioEntity, Long> {

    @Autowired
    DomicilioRepository domicilioRepository;

    @Override
    public void create(DomicilioEntity entidad) {

    }

    @Override
    public DomicilioEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<DomicilioEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(DomicilioEntity entidad) {

    }

    @Override
    public void actualizar(DomicilioEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}