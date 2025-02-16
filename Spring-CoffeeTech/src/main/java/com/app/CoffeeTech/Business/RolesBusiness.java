package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.RolesDTO;
import com.app.CoffeeTech.Entity.RolesEntity;
import com.app.CoffeeTech.Service.RolesService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RolesBusiness {

    @Autowired
    RolesService rolesService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Deliveries
    public Page<RolesDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<RolesEntity> rolesList = rolesService.findAll(pageRequest);
            if (rolesList.isEmpty()){
                return Page.empty();
            }
            return rolesList.map(rolesEntity -> modelMapper.map(rolesEntity, RolesDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los roles." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Roles by ID
    public RolesDTO findById(Long id){
        try {
            RolesEntity rolesEntity = rolesService.getById(id);
            if (rolesEntity == null){
                throw new CustomException("Rol con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(rolesEntity, RolesDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener el rol por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Roles
    public void add(RolesDTO rolesDto){
        try {
            RolesEntity rolesEntity = modelMapper.map(rolesDto, RolesEntity.class);
            rolesService.save(rolesEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando el rol." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Roles
    public void update(Long id, RolesDTO rolesDto) {
        try {
            RolesEntity existingRole = rolesService.getById(id);
            if (existingRole == null) {
                throw new CustomException("Rol con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            existingRole.setNombreRol(rolesDto.getNombreRol());
            rolesService.save(existingRole);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el rol." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Roles
    public void delete(Long idRoles) {
        try {
            RolesEntity rolesEntity = rolesService.getById(idRoles);
            if (rolesEntity == null) {
                throw new CustomException("Rol con id " + idRoles + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            rolesService.delete(rolesEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el rol: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}