package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.RolesHasPersonasBusiness;
import com.app.CoffeeTech.DTO.RolesHasPersonasDTO;
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
@RequestMapping("/api/rolesHasPersonas")
public class RolesHasPersonasController {

    @Autowired
    RolesHasPersonasBusiness rolesHasPersonasBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<RolesHasPersonasDTO>> getAllRolesHasPersonas() {
        List<RolesHasPersonasDTO> rolesHasPersonasList = rolesHasPersonasBusiness.findAll();
        if (rolesHasPersonasList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(rolesHasPersonasList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getRolesHasPersonasById(@PathVariable Long id) {
        try {
            RolesHasPersonasDTO rolesHasPersonas = rolesHasPersonasBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", rolesHasPersonas);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createRolesHasPersonas(@Validated @RequestBody RolesHasPersonasDTO rolesHasPersonasDto) {
        try {
            rolesHasPersonasBusiness.create(rolesHasPersonasDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Roles Has Persons Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateRolesHasPersonas(@PathVariable Long id, @Validated @RequestBody RolesHasPersonasDTO rolesHasPersonasDto) {
        try {
            rolesHasPersonasBusiness.update(id, rolesHasPersonasDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Roles Has Persons Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteRolesHasPersonas(@PathVariable Long id) {
        try {
            rolesHasPersonasBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Roles Has Persons Deleted Successfully");
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