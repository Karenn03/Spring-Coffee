package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PersonaDTO;
import com.app.CoffeeTech.Entity.PersonaEntity;
import com.app.CoffeeTech.Service.PersonaService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PersonaBusiness {

    @Autowired
    PersonaService personaService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All People
    public Page<PersonaDTO> findAll(Pageable pageable){
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<PersonaEntity> personaPage = personaService.findAll(pageRequest);
            if (personaPage.isEmpty()) {
                return Page.empty();
            }
            return personaPage.map(personaEntity -> modelMapper.map(personaEntity, PersonaDTO.class));
        } catch (Exception e) {
            throw new CustomException("Error al obtener todas las personas." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Person by ID
    public PersonaDTO findById(Long id) {
        try {
            PersonaEntity personaEntity = personaService.getById(id);
            if (personaEntity == null) {
                throw new CustomException("Persona con id: " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(personaEntity, PersonaDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al obtener la persona por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Person
    public void add(PersonaDTO personaDto) {
        try {
            PersonaEntity personaEntity = modelMapper.map(personaDto, PersonaEntity.class);
            personaService.save(personaEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error creando la persona: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Person
    public void update(Long id, PersonaDTO personaDto) {
        try {
            PersonaEntity existingPerson = personaService.getById(id);
            if (existingPerson == null) {
                throw new CustomException("Persona con id: " + id + " no se encuentra.", HttpStatus.NOT_FOUND);
            }
            existingPerson.setDocumento(personaDto.getDocumento());
            existingPerson.setNombres(personaDto.getNombres());
            existingPerson.setApellidos(personaDto.getApellidos());
            existingPerson.setCorreoElectronico(personaDto.getCorreoElectronico());
            existingPerson.setContraseña(personaDto.getContraseña());
            existingPerson.setTelefono(personaDto.getTelefono());
            existingPerson.setDireccion(personaDto.getDireccion());
            personaService.save(existingPerson);
            personaService.save(existingPerson);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la persona: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Person
    public void delete(Long idPersona) {
        try {
            PersonaEntity personaEntity = personaService.getById(idPersona);
            if (personaEntity == null) {
                throw new CustomException("Persona con id " + idPersona + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            personaService.delete(personaEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la persona: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}