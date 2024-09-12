package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PromocionesDTO;
import com.app.CoffeeTech.Entity.PromocionesEntity;
import com.app.CoffeeTech.Service.PromocionesService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PromocionesBusiness {

    @Autowired
    PromocionesService promocionesService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<PromocionesDTO> findAll(){
        try {
            List<PromocionesEntity> promocionesList = promocionesService.findAll();
            if (promocionesList.isEmpty()){
                return new ArrayList<>();
            }
            List<PromocionesDTO> promocionesDtoList = new ArrayList<>();
            promocionesList.forEach(PromocionesEntity -> promocionesDtoList.add(modelMapper.map(PromocionesEntity, PromocionesDTO.class)));
            return promocionesDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las promociones.");
        }
    }

    public PromocionesDTO getById(Long id){
        try {
            PromocionesEntity promocionesEntity = promocionesService.getById(id);
            if (promocionesEntity == null){
                throw new CustomException("Promoción con id " + id + " no encontrada.");
            }
            return modelMapper.map(promocionesEntity, PromocionesDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener la promoción por id.");
        }
    }

    public void update(Long id, PromocionesDTO promocionesDto) {
        try {
            PromocionesEntity existingPromotion = promocionesService.getById(id);
            if (existingPromotion == null) {
                throw new CustomException("Promoción con id " + id + " no se encuentra.");
            }
            existingPromotion.setNombrePromo(promocionesDto.getNombrePromo());
            promocionesService.save(existingPromotion);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la promoción.");
        }
    }

    public void create(PromocionesDTO promocionesDto){
        try {
            PromocionesEntity promocionesEntity = modelMapper.map(promocionesDto, PromocionesEntity.class);
            promocionesService.save(promocionesEntity);
        } catch (Exception e){
            throw new CustomException("Error creando la promoción.");
        }
    }

    public void delete(Long idPromociones) {
        try {
            PromocionesEntity promocionesEntity = promocionesService.getById(idPromociones);
            if (promocionesEntity == null) {
                throw new CustomException("Promoción con id " + idPromociones + " no encontrada.");
            }
            promocionesService.delete(promocionesEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando la promoción: " + e.getMessage());
        }
    }
}