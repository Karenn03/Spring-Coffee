package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PromocionesHasProductosDTO;
import com.app.CoffeeTech.Entity.PromocionesHasProductosEntity;
import com.app.CoffeeTech.Service.PromocionesHasProductosService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PromocionesHasProductosBusiness {

    @Autowired
    PromocionesHasProductosService promocionesHasProductosService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<PromocionesHasProductosDTO> findAll(){
        try {
            List<PromocionesHasProductosEntity> promocionesHasProductosList = promocionesHasProductosService.findAll();
            if (promocionesHasProductosList.isEmpty()){
                return new ArrayList<>();
            }
            List<PromocionesHasProductosDTO> promocionesHasProductosDtoList = new ArrayList<>();
            promocionesHasProductosList.forEach(PromocionesHasProductosEntity -> promocionesHasProductosDtoList.add(modelMapper.map(PromocionesHasProductosEntity, PromocionesHasProductosDTO.class)));
            return promocionesHasProductosDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los 'promocionesHasProductos'.");
        }
    }

    public PromocionesHasProductosDTO getById(Long id){
        try {
            PromocionesHasProductosEntity promocionesHasProductosEntity = promocionesHasProductosService.getById(id);
            if (promocionesHasProductosEntity == null){
                throw new CustomException("'PromocionesHasProductos' con id " + id + " no encontrado.");
            }
            return modelMapper.map(promocionesHasProductosEntity, PromocionesHasProductosDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener el 'promocionesHasProductos' por id.");
        }
    }

    public void update(Long id, PromocionesHasProductosDTO promocionesHasProductosDto) {
        try {
            PromocionesHasProductosEntity existingPromotionsHasProducts = promocionesHasProductosService.getById(id);
            if (existingPromotionsHasProducts == null) {
                throw new CustomException("'PromocionesHasProductos' con id " + id + " no se encuentra.");
            }
            existingPromotionsHasProducts.setIdPromocionesHasPoductos(promocionesHasProductosDto.getIdPromocionesHasPoductos());
            promocionesHasProductosService.save(existingPromotionsHasProducts);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el 'promocionesHasProductos'.");
        }
    }

    public void create(PromocionesHasProductosDTO promocionesHasProductosDto){
        try {
            PromocionesHasProductosEntity promocionesHasProductosEntity = modelMapper.map(promocionesHasProductosDto, PromocionesHasProductosEntity.class);
            promocionesHasProductosService.save(promocionesHasProductosEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el 'promocionesHasProductos'.");
        }
    }

    public void delete(Long idPromocionesHasProductos) {
        try {
            PromocionesHasProductosEntity promocionesHasProductosEntity = promocionesHasProductosService.getById(idPromocionesHasProductos);
            if (promocionesHasProductosEntity == null) {
                throw new CustomException("'PromocionesHasProductos' con id " + idPromocionesHasProductos + " no encontrado.");
            }
            promocionesHasProductosService.delete(promocionesHasProductosEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el 'promocionesHasProductos': " + e.getMessage());
        }
    }
}