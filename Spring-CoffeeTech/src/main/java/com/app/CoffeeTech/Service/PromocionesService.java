package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.PromocionesEntity;
import com.app.CoffeeTech.Repository.PromocionesRespository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PromocionesService implements IDAO<PromocionesEntity, Long> {

    @Autowired
    PromocionesRespository promocionesRespository;

    @Override
    public void create(PromocionesEntity entidad) {

    }

    @Override
    public PromocionesEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<PromocionesEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(PromocionesEntity entidad) {

    }

    @Override
    public void actualizar(PromocionesEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}
