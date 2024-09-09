package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.PersonaDTO;
import com.app.CoffeeTech.Entity.PersonaEntity;
import com.app.CoffeeTech.Service.PersonaService;
import com.app.CoffeeTech.Utilities.CustomException;
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

    //Metodo para traer todas las personas
    public List<PersonaDTO> findAll(){
        try {
            List<PersonaEntity> personasList = personaService.findAll();
            if (personasList.isEmpty()){
                return new ArrayList<>();
            }
            List<PersonaDTO> personasDtoList = new ArrayList<>();
            personasList.forEach(PersonaEntity->personasDtoList.add(modelMapper.map(PersonaEntity, PersonaDTO.class)));
            return personasDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las personas.");
        }
    }

    //Metodo para traer a una persona por ID
    public PersonaDTO getById (Long id){
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

    //Netodo para actualizar una persona
    public void update(PersonaDTO personaDto){
        try {
            PersonaEntity personaEntity = modelMapper.map(personaDto, PersonaEntity.class);
            personaService.save(personaEntity);
        }catch (Exception e){
            throw new CustomException("Error al actualizar la persona.");
        }
    }

    //Metodo para crear, guardar una persona
    public void create(PersonaDTO personaDto){
        try {
            String Documento = personaDto.getDocumento();
            PersonaEntity existingPerson = personaService.findByDocument(Documento);
            if (existingPerson != null){
                throw new CustomException("La persona con el documento " + Documento + " ya existe.");
            }
            PersonaEntity personaEntity = modelMapper.map(personaDto, PersonaEntity.class);
            personaService.save(personaEntity);
        }catch (Exception e){
            throw new CustomException("Error creando la persona.");
        }
    }

    //Metodo para eliminar una persona
    public void delete(Long idPersonas){
        try {
            PersonaEntity personaEntity = personaService.getById(idPersonas);
            if (personaEntity == null){
                throw new CustomException("Persona con id " + idPersonas + " no encontrada.");
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}