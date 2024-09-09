package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.RolesDTO;
import com.app.CoffeeTech.Entity.RolesEntity;
import com.app.CoffeeTech.Service.RolesService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RolesBusiness {

    @Autowired
    RolesService rolesService;

    private final ModelMapper modelMapper = new ModelMapper();

    //Metodo para traer todos los roles
    public List<RolesDTO> findAll(){
        try {
            List<RolesEntity> rolesList = rolesService.findAll();
            if (rolesList.isEmpty()){
                return new ArrayList<>();
            }
            List<RolesDTO> rolesDtoList = new ArrayList<>();
            rolesList.forEach(RolesEntity -> rolesDtoList.add(modelMapper.map(RolesEntity, RolesDTO.class)));
            return rolesDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los roles.");
        }
    }

    //Metodo para buscar por id
    public RolesDTO getById(Long id){
        try {
            RolesEntity rolesEntity = rolesService.getById(id);
            if (rolesEntity == null){
                throw new CustomException("Rol con id " + id + " no encontrado.");
            }
            return modelMapper.map(rolesEntity, RolesDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener el rol por id.");
        }
    }

    // Método para actualizar un rol
    public void update(Long id, RolesDTO rolesDto) {
        try {
            RolesEntity existingRole = rolesService.getById(id);
            if (existingRole == null) {
                throw new CustomException("Rol con id " + id + " no se encuentra.");
            }
            existingRole.setNombreRol(rolesDto.getNombreRol());
            rolesService.save(existingRole);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el rol.");
        }
    }

    //Metodo para Crear, Guardar un nuevo Rol
    public void create(RolesDTO rolesDto){
        try {
            String nombreRol = rolesDto.getNombreRol();
            RolesEntity existingRole = rolesService.findByNombreRol(nombreRol);
            if (existingRole != null) {
                throw new CustomException("El rol con el nombre " + nombreRol + " ya existe.");
            }
            RolesEntity rolesEntity = modelMapper.map(rolesDto, RolesEntity.class);
            rolesService.save(rolesEntity);
        } catch (Exception e){
            throw new CustomException("Error creando el rol.");
        }
    }

    // Metodo para eliminar un rol
    public void delete(Long idRol) {
        try {
            RolesEntity rolesEntity = rolesService.getById(idRol);
            if (rolesEntity == null) {
                throw new CustomException("Rol con id " + idRol + " no encontrado.");
            }
            rolesService.delete(rolesEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando el rol: " + e.getMessage());
        }
    }
}