package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.VentasEntity;
import com.app.CoffeeTech.Repository.VentasRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class VentasService implements IDAO<VentasEntity, Long> {

    @Autowired
    VentasRepository ventasRepository;

    @Override
    public void create(VentasEntity entidad) {

    }

    @Override
    public VentasEntity buscarPorId(Long aLong) {
        return null;
    }

    @Override
    public List<VentasEntity> buscarTodos() {
        return List.of();
    }

    @Override
    public void guardar(VentasEntity entidad) {

    }

    @Override
    public void actualizar(VentasEntity entidad) {

    }

    @Override
    public void eliminar(Long aLong) {

    }
}