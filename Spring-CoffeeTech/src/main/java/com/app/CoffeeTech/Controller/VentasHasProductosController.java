package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.VentasHasProductosBusiness;
import com.app.CoffeeTech.DTO.VentasHasProductosDTO;
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
@RequestMapping("/api/ventasHasProductos")
public class VentasHasProductosController {

    @Autowired
    VentasHasProductosBusiness ventasHasProductosBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<VentasHasProductosDTO>> getAllVentasHasProductos() {
        List<VentasHasProductosDTO> ventasHasProductosList = ventasHasProductosBusiness.findAll();
        if (ventasHasProductosList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(ventasHasProductosList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getVentasHasProductosById(@PathVariable Long id) {
        try {
            VentasHasProductosDTO ventasHasProductos = ventasHasProductosBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", ventasHasProductos);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createVentasHasProductos(@Validated @RequestBody VentasHasProductosDTO ventasHasProductosDto) {
        try {
            ventasHasProductosBusiness.create(ventasHasProductosDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Sales Has Products Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateVentasHasProductos(@PathVariable Long id, @Validated @RequestBody VentasHasProductosDTO ventasHasProductosDto) {
        try {
            ventasHasProductosBusiness.update(id, ventasHasProductosDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Sales Has Products Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteVentasHasProductos(@PathVariable Long id) {
        try {
            ventasHasProductosBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Sales Has Products Deleted Successfully");
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