package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.PromocionesHasProductosBusiness;
import com.app.CoffeeTech.DTO.PromocionesHasProductosDTO;
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
@RequestMapping("/api/promocionesHasProductos")
public class PromocionesHasProductosController {

    @Autowired
    PromocionesHasProductosBusiness promocionesHasProductosBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<PromocionesHasProductosDTO>> getAllPromocionesHasProductos() {
        List<PromocionesHasProductosDTO> promocionesHasProductosList = promocionesHasProductosBusiness.findAll();
        if (promocionesHasProductosList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(promocionesHasProductosList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPromocionesHasProductosById(@PathVariable Long id) {
        try {
            PromocionesHasProductosDTO promocionesHasProductos = promocionesHasProductosBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", promocionesHasProductos);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPromocionesHasProductos(@Validated @RequestBody PromocionesHasProductosDTO promocionesHasProductosDto) {
        try {
            promocionesHasProductosBusiness.create(promocionesHasProductosDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Promotions Has Products Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updatePromocionesHasProductos(@PathVariable Long id, @Validated @RequestBody PromocionesHasProductosDTO promocionesHasProductosDto) {
        try {
            promocionesHasProductosBusiness.update(id, promocionesHasProductosDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Promotions Has Products Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePromocionesHasProductos(@PathVariable Long id) {
        try {
            promocionesHasProductosBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Promotions Has Products Deleted Successfully");
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