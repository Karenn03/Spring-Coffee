package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.Entity.DomicilioEntity;
import com.app.CoffeeTech.Repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DomicilioBusiness {

    @Autowired
    DomicilioRepository domicilioRepository;

    public List<DomicilioEntity> getAllDomicilio() {
        return domicilioRepository.findAll();
    }

    public Optional<DomicilioEntity> getDomicilioById(Long id) {
        return domicilioRepository.findById(id);
    }

    public DomicilioEntity createDomicilio(DomicilioEntity domicilio) {
        return domicilioRepository.save(domicilio);
    }

    public DomicilioEntity updateDomicilio(Long id, DomicilioEntity domicilioDetails) {
        Optional<DomicilioEntity> domicilio = domicilioRepository.findById(id);
        if (domicilio.isPresent()) {
            DomicilioEntity existingDomicilio = domicilio.get();
            existingDomicilio.setDireccion(domicilioDetails.getDireccion());
            existingDomicilio.setEspecificaciones(domicilioDetails.getEspecificaciones());
            return domicilioRepository.save(existingDomicilio);
        } else {
            throw new RuntimeException("Domicilio con id: " + id + " no encontrado");
        }
    }

    public void deleteCarritoCompras(Long id) {
        domicilioRepository.deleteById(id);
    }

}