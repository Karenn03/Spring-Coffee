package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PrivilegiosDTO;
import com.app.CoffeeTech.Entity.PrivilegiosEntity;
import com.app.CoffeeTech.Service.PrivilegiosService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PrivilegiosBusiness {

    @Autowired
    PrivilegiosService privilegiosService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Privileges
    public Page<PrivilegiosDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<PrivilegiosEntity> privilegiosList = privilegiosService.findAll(pageRequest);
            if (privilegiosList.isEmpty()){
                return Page.empty();
            }
            return privilegiosList.map(privilegiosEntity -> modelMapper.map(privilegiosEntity, PrivilegiosDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todos los privilegios." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Privilege by ID
    public PrivilegiosDTO findById(Long id){
        try {
            PrivilegiosEntity privilegiosEntity = privilegiosService.getById(id);
            if (privilegiosEntity == null){
                throw new CustomException("Privilegio con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(privilegiosEntity, PrivilegiosDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener el privilegio por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Privilege
    public void add(PrivilegiosDTO privilegiosDto){
        try {
            PrivilegiosEntity privilegiosEntity = modelMapper.map(privilegiosDto, PrivilegiosEntity.class);
            privilegiosService.save(privilegiosEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando el privilegio." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Privilege
    public void update(Long id, PrivilegiosDTO privilegiosDto) {
        try {
            PrivilegiosEntity existingPrivilege = privilegiosService.getById(id);
            if (existingPrivilege == null) {
                throw new CustomException("Privilegio con id " + id + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            existingPrivilege.setNombrePrivilegio(privilegiosDto.getNombrePrivilegio());
            privilegiosService.save(existingPrivilege);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar el privilegio." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Privilege
    public void delete(Long idPrivilegios) {
        try {
            PrivilegiosEntity privilegiosEntity = privilegiosService.getById(idPrivilegios);
            if (privilegiosEntity == null) {
                throw new CustomException("Privilegio con id " + idPrivilegios + " no encontrado.", HttpStatus.NOT_FOUND);
            }
            privilegiosService.delete(privilegiosEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando el privilegio: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
