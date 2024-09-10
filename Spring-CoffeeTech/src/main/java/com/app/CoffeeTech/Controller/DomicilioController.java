package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.DomicilioBusiness;
import com.app.CoffeeTech.DTO.DomicilioDTO;
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
@RequestMapping("/api/domicilio")
public class DomicilioController {

    @Autowired
    DomicilioBusiness domicilioBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<DomicilioDTO>> getAllDomicilios() {
        List<DomicilioDTO> domiciliosList = domicilioBusiness.findAll();
        if (domiciliosList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(domiciliosList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getDomicilioById(@PathVariable Long id) {
        try {
            DomicilioDTO domicilio = domicilioBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", domicilio);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createDomicilio(@Validated @RequestBody DomicilioDTO domicilioDto) {
        try {
            domicilioBusiness.create(domicilioDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Delivery Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateDomicilio(@PathVariable Long id, @Validated @RequestBody DomicilioDTO domicilioDto) {
        try {
            domicilioBusiness.update(id, domicilioDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Delivery Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteDomicilio(@PathVariable Long id) {
        try {
            domicilioBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Delivery Deleted Successfully");
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