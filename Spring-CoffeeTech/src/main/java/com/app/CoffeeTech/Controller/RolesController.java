package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.RolesBusiness;
import com.app.CoffeeTech.DTO.RolesDTO;
import com.app.CoffeeTech.Utilities.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RolesController {

    @Autowired
    RolesBusiness rolesBusiness;

    //Metodo para mostrar - traer  todos los Roles
    @GetMapping("/all")
    public ResponseEntity<List<RolesDTO>> getAllRoles() {
        List<RolesDTO> rolesList = rolesBusiness.findAll();
        if (rolesList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(rolesList);
        }
    }

    // Método para obtener un Rol por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getRolById(@PathVariable Long id) {
        try {
            RolesDTO roles = rolesBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", roles);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    //Metodo para crear un Rol
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createRol(@Validated @RequestBody RolesDTO rolesDto) {
        try {
            rolesBusiness.create(rolesDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Rol Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateRol(@PathVariable Long id, @Validated @RequestBody RolesDTO rolesDto) {
        try {
            rolesBusiness.update(id, rolesDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Role Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteRol(@PathVariable Long id) {
        try {
            rolesBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Rol Deleted Successfully");
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