package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.UserDTO;
import com.app.CoffeeTech.Entity.UserEntity;
import com.app.CoffeeTech.Service.UserService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserBusiness {

    @Autowired
    UserService userService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All People
    public Page<UserDTO> findAll(Pageable pageable){
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<UserEntity> personaPage = userService.findAll(pageRequest);
            if (personaPage.isEmpty()) {
                return Page.empty();
            }
            return personaPage.map(personaEntity -> modelMapper.map(personaEntity, UserDTO.class));
        } catch (Exception e) {
            throw new CustomException("Error al obtener todas las personas." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Person by ID
    public UserDTO findById(Long id) {
        try {
            UserEntity userEntity = userService.getById(id);
            if (userEntity == null) {
                throw new CustomException("Persona con id: " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(userEntity, UserDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al obtener la persona por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Person
    public void add(UserDTO userDto) {
        try {
            UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
            userService.save(userEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error creando la persona: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Person
    public void update(Long id, UserDTO userDto) {
        try {
            UserEntity existingPerson = userService.getById(id);
            if (existingPerson == null) {
                throw new CustomException("Persona con id: " + id + " no se encuentra.", HttpStatus.NOT_FOUND);
            }
            existingPerson.setDocumento(userDto.getDocumento());
            existingPerson.setNombres(userDto.getNombres());
            existingPerson.setApellidos(userDto.getApellidos());
            existingPerson.setCorreoElectronico(userDto.getCorreoElectronico());
            existingPerson.setContraseña(userDto.getContraseña());
            existingPerson.setTelefono(userDto.getTelefono());
            existingPerson.setDireccion(userDto.getDireccion());
            userService.save(existingPerson);
            userService.save(existingPerson);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la persona: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Person
    public void delete(Long idPersona) {
        try {
            UserEntity userEntity = userService.getById(idPersona);
            if (userEntity == null) {
                throw new CustomException("Persona con id " + idPersona + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            userService.delete(userEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la persona: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}