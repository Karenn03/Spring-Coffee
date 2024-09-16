package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.RolesHasPersonasDTO;
import com.app.CoffeeTech.Entity.RolesHasPersonasEntity;
import com.app.CoffeeTech.Service.RolesHasPersonasService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RolesHasPersonasBusiness {

    @Autowired
    RolesHasPersonasService rolesHasPersonasService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<RolesHasPersonasDTO> findAll(){
        try {
            List<RolesHasPersonasEntity> rolesHasPersonasList = rolesHasPersonasService.findAll();
            if (rolesHasPersonasList.isEmpty()){
                return new ArrayList<>();
            }
            List<RolesHasPersonasDTO> rolesHasPersonasDtoList = new ArrayList<>();
            rolesHasPersonasList.forEach(RolesHasPersonasEntity -> rolesHasPersonasDtoList.add(modelMapper.map(RolesHasPersonasEntity, RolesHasPersonasDTO.class)));
            return rolesHasPersonasDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los 'roles has personas'.");
        }
    }

    public RolesHasPersonasDTO getById(Long id){
        try {
            RolesHasPersonasEntity rolesHasPersonasEntity = rolesHasPersonasService.getById(id);
            if (rolesHasPersonasEntity == null){
                throw new CustomException("'Roles has personas' con id " + id + " no encontrado.");
            }
            return modelMapper.map(rolesHasPersonasEntity, RolesHasPersonasDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener el 'roles has personas' por id.");
        }
    }

    public void update(Long id, RolesHasPersonasDTO rolesHasPersonasDto) {
        try {
            RolesHasPersonasEntity existingRoleHasPersons = rolesHasPersonasService.getById(id);
            if (existingRoleHasPersons == null) {
                throw new CustomException("'Roles has personas' con id " + id + " no se encuentra.");
            }
            existingRoleHasPersons.setIdRolesHasPersonas(rolesHasPersonasDto.getIdRolesHasPersonas());
            rolesHasPersonasService.save(existingRoleHasPersons);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el 'roles has personas'.");
        }
    }

    public void create(RolesHasPersonasDTO rolesHasPersonasDto){
        try {
            RolesHasPersonasEntity rolesHasPersonasEntity = modelMapper.map(rolesHasPersonasDto, RolesHasPersonasEntity.class);
            rolesHasPersonasService.save(rolesHasPersonasEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el 'roles has personas'.");
        }
    }

    public void delete(Long idRolesHasPersonas) {
        try {
            RolesHasPersonasEntity rolesHasPersonasEntity = rolesHasPersonasService.getById(idRolesHasPersonas);
            if (rolesHasPersonasEntity == null) {
                throw new CustomException("'Roles has personas' con id " + idRolesHasPersonas + " no encontrado.");
            }
            rolesHasPersonasService.delete(rolesHasPersonasEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el 'roles has personas': " + e.getMessage());
        }
    }
}