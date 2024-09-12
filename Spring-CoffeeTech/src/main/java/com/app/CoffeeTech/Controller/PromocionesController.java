package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.PromocionesBusiness;
import com.app.CoffeeTech.DTO.PromocionesDTO;
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
@RequestMapping("/api/promociones")
public class PromocionesController {

    @Autowired
    PromocionesBusiness promocionesBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<PromocionesDTO>> getAllPromociones() {
        List<PromocionesDTO> promocionesList = promocionesBusiness.findAll();
        if (promocionesList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(promocionesList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPromocionById(@PathVariable Long id) {
        try {
            PromocionesDTO promociones = promocionesBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", promociones);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPromocion(@Validated @RequestBody PromocionesDTO promocionesDto) {
        try {
            promocionesBusiness.create(promocionesDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Promotion Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updatePromocion(@PathVariable Long id, @Validated @RequestBody PromocionesDTO promocionesDto) {
        try {
            promocionesBusiness.update(id, promocionesDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Promotion Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePromocion(@PathVariable Long id) {
        try {
            promocionesBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Promotion Deleted Successfully");
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