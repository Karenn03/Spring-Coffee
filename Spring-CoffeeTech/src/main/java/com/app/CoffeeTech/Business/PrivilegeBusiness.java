package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PrivilegeDTO;
import com.app.CoffeeTech.Entity.PrivilegeEntity;
import com.app.CoffeeTech.Service.PrivilegeService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeBusiness {

    @Autowired
    PrivilegeService privilegeService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Privileges
    public Page<PrivilegeDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<PrivilegeEntity> privilegiosList = privilegeService.findAll(pageRequest);
            if (privilegiosList.isEmpty()){
                return Page.empty();
            }
            return privilegiosList.map(privilegiosEntity -> modelMapper.map(privilegiosEntity, PrivilegeDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los privilegios." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Privilege by ID
    public PrivilegeDTO findById(Long id){
        try {
            PrivilegeEntity privilegeEntity = privilegeService.getById(id);
            if (privilegeEntity == null){
                throw new CustomException("Privilegio con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(privilegeEntity, PrivilegeDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener el privilegio por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Privilege
    public void add(PrivilegeDTO privilegeDto){
        try {
            PrivilegeEntity privilegeEntity = modelMapper.map(privilegeDto, PrivilegeEntity.class);
            privilegeService.save(privilegeEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando el privilegio." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Privilege
    public void update(Long id, PrivilegeDTO privilegeDto) {
        try {
            PrivilegeEntity existingPrivilege = privilegeService.getById(id);
            if (existingPrivilege == null) {
                throw new CustomException("Privilegio con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            existingPrivilege.setNombrePrivilegio(privilegeDto.getNombrePrivilegio());
            privilegeService.save(existingPrivilege);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el privilegio." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Privilege
    public void delete(Long idPrivilegios) {
        try {
            PrivilegeEntity privilegeEntity = privilegeService.getById(idPrivilegios);
            if (privilegeEntity == null) {
                throw new CustomException("Privilegio con id " + idPrivilegios + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            privilegeService.delete(privilegeEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el privilegio: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
