package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Entity.MesasEntity;
import com.app.CoffeeTech.Repository.MesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MesasBusiness {

    @Autowired
    MesasRepository mesasRepository;

    public List<MesasEntity> getAllMesas() {
        return mesasRepository.findAll();
    }

    public Optional<MesasEntity> getMesasById(Long id) {
        return mesasRepository.findById(id);
    }

    public MesasEntity createMesas(MesasEntity Mesas) {
        return mesasRepository.save(Mesas);
    }

    public MesasEntity updateMesas(Long id, MesasEntity mesasDetails) {
        Optional<MesasEntity> Mesas = mesasRepository.findById(id);
        if (Mesas.isPresent()) {
            MesasEntity existingMesas = Mesas.get();
            existingMesas.setNumeroMesa(mesasDetails.getNumeroMesa());
            existingMesas.setCapacidad(mesasDetails.getCapacidad());
            return mesasRepository.save(existingMesas);
        } else {
            throw new RuntimeException("Mesa con id: " + id + " no encontrada");
        }
    }

    public void deleteMesas(Long id) {
        mesasRepository.deleteById(id);
    }

}