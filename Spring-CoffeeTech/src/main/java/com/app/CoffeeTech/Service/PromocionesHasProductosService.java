package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.PromocionesHasProductosEntity;
import com.app.CoffeeTech.Repository.PromocionesHasProductosRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PromocionesHasProductosService implements IDAO<PromocionesHasProductosEntity, Long> {

    @Autowired
    PromocionesHasProductosRepository promocionesHasProductosRepository;

    @Override
    public void create(PromocionesHasProductosEntity entidad) {

    }

    @Override
    public PromocionesHasProductosEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<PromocionesHasProductosEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(PromocionesHasProductosEntity entidad) {

    }

    @Override
    public void actualizar(PromocionesHasProductosEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}
