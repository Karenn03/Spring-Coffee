package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.PersonaBusiness;
import com.app.CoffeeTech.DTO.PersonaDTO;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    PersonaBusiness personaBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<PersonaDTO>> getAllPersonas() {
        List<PersonaDTO> personasList = personaBusiness.findAll();
        if (personasList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(personasList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPersonaById(@PathVariable Long id) {
        try {
            PersonaDTO personas = personaBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", personas);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPersona(@Validated @RequestBody PersonaDTO personaDto) {
        try {
            personaBusiness.create(personaDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Person Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updatePersona(@PathVariable Long id, @Validated @RequestBody PersonaDTO personaDto) {
        try {
            personaBusiness.update(id, personaDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Person Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePerson(@PathVariable Long id) {
        try {
            personaBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Person Deleted Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    private ResponseEntity<Map<String, Object>> handleException(CustomException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}