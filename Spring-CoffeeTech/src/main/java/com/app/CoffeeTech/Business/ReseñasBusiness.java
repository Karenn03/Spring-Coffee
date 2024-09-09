package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.ReseñasDTO;
import com.app.CoffeeTech.Entity.ReseñasEntity;
import com.app.CoffeeTech.Service.ReseñasService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReseñasBusiness {

    @Autowired
    ReseñasService reseñasService;

    private final ModelMapper modelMapper = new ModelMapper();

    //Metodo para traer todas las reseñas
    public List<ReseñasDTO> findAll(){
        try {
            List<ReseñasEntity> reseñasList = reseñasService.findAll();
            if (reseñasList.isEmpty()){
                return new ArrayList<>();
            }
            List<ReseñasDTO> reseñasDtoList = new ArrayList<>();
            reseñasList.forEach(ReseñasEntity -> reseñasDtoList.add(modelMapper.map(ReseñasEntity, ReseñasDTO.class)));
            return reseñasDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las reseñas.");
        }
    }

    //Metodo para buscar por id
    public ReseñasDTO getById(Long id){
        try {
            ReseñasEntity reseñasEntity = reseñasService.getById(id);
            if (reseñasEntity == null){
                throw new CustomException("Reseña con id " + id + " no encontrada.");
            }
            return modelMapper.map(reseñasEntity, ReseñasDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener la reseña por id.");
        }
    }

    // Método para actualizar una reseña
    public void update(Long id, ReseñasDTO reseñasDto) {
        try {
            ReseñasEntity existingReview = reseñasService.getById(id);
            if (existingReview == null) {
                throw new CustomException("Reseña con id " + id + " no se encuentra.");
            }
            existingReview.setCalificacion(reseñasDto.getCalificacion());
            reseñasService.save(existingReview);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la reseña.");
        }
    }

    //Metodo para crear, guardar una nueva reseña
    public void create(ReseñasDTO reseñasDto){
        try {
            Long IdReseñas = reseñasDto.getIdReseñas();
            ReseñasEntity existingReview = reseñasService.getById(IdReseñas);
            if (existingReview != null) {
                throw new CustomException("La reseña con el id " + IdReseñas + " ya existe.");
            }
            ReseñasEntity reseñasEntity = modelMapper.map(reseñasDto, ReseñasEntity.class);
            reseñasService.save(reseñasEntity);
        } catch (Exception e){
            throw new CustomException("Error creando la reseña.");
        }
    }

    // Metodo para eliminar una reseña
    public void delete(Long idReseñas) {
        try {
            ReseñasEntity reseñasEntity = reseñasService.getById(idReseñas);
            if (reseñasEntity == null) {
                throw new CustomException("Reseña con id " + idReseñas + " no encontrada.");
            }
            reseñasService.delete(reseñasEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando la reseña: " + e.getMessage());
        }
    }
}