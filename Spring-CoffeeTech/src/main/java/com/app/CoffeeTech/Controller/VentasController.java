package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.VentasBusiness;
import com.app.CoffeeTech.DTO.VentasDTO;
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
@RequestMapping("/api/ventas")
public class VentasController {
    @Autowired
    VentasBusiness ventasBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<VentasDTO>> getAllVentas() {
        List<VentasDTO> ventasList = ventasBusiness.findAll();
        if (ventasList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(ventasList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getVentaById(@PathVariable Long id) {
        try {
            VentasDTO venta = ventasBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", venta);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createVenta(@Validated @RequestBody VentasDTO ventasDto) {
        try {
            ventasBusiness.create(ventasDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Sale Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateVenta(@PathVariable Long id, @Validated @RequestBody VentasDTO ventasDto) {
        try {
            ventasBusiness.update(id, ventasDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Sale Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteVenta(@PathVariable Long id) {
        try {
            ventasBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Sale Deleted Successfully");
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