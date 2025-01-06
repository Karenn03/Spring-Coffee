package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PersonaDTO;
import com.app.CoffeeTech.Entity.PersonaEntity;
import com.app.CoffeeTech.Service.PersonaService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonaBusiness {

    @Autowired
    PersonaService personaService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<PersonaDTO> findAll(){
        try {
            List<PersonaEntity> personasList = personaService.findAll();
            if (personasList.isEmpty()){
                return new ArrayList<>();
            }
            List<PersonaDTO> personasDtoList = new ArrayList<>();
            personasList.forEach(PersonaEntity -> personasDtoList.add(modelMapper.map(PersonaEntity, PersonaDTO.class)));
            return personasDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las personas.");
        }
    }

    public PersonaDTO getById(Long id){
        try {
            PersonaEntity personaEntity = personaService.getById(id);
            if (personaEntity == null){
                throw new CustomException("Persona con id " + id + " no encontrada.");
            }
            return modelMapper.map(personaEntity, PersonaDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener la persona por id.");
        }
    }

    public void update(Long id, PersonaDTO personaDto) {
        try {
            PersonaEntity existingPerson = personaService.getById(id);
            if (existingPerson == null) {
                throw new CustomException("Persona con id " + id + " no se encuentra.");
            }
            existingPerson.setDocumento(personaDto.getDocumento());
            existingPerson.setNombres(personaDto.getNombres());
            existingPerson.setApellidos(personaDto.getApellidos());
            existingPerson.setCorreoElectronico(personaDto.getCorreoElectronico());
            existingPerson.setContraseña(personaDto.getContraseña());
            existingPerson.setTelefono(personaDto.getTelefono());
            existingPerson.setDireccion(personaDto.getDireccion());
            personaService.save(existingPerson);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la persona.");
        }
    }

    public void create(PersonaDTO personaDto){
        try {
            String Documento = personaDto.getDocumento();
            PersonaEntity existingPerson = personaService.findByDocument(Documento);
            if (existingPerson != null) {
                throw new CustomException("La persona con el documento " + Documento + " ya existe.");
            }
            PersonaEntity personaEntity = modelMapper.map(personaDto, PersonaEntity.class);
            personaService.save(personaEntity);
        } catch (Exception e){
            throw new CustomException("Error creando la persona.");
        }
    }

    public void delete(Long idPersonas) {
        try {
            PersonaEntity personaEntity = personaService.getById(idPersonas);
            if (personaEntity == null) {
                throw new CustomException("Persona con id " + idPersonas + " no encontrada.");
            }
            personaService.delete(personaEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando la persona: " + e.getMessage());
        }
    }
}