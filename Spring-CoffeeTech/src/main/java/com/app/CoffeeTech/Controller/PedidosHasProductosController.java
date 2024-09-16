package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.PedidosHasProductosBusiness;
import com.app.CoffeeTech.DTO.PedidosHasProductosDTO;
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
@RequestMapping("/api/pedidosHasProductos")
public class PedidosHasProductosController {

    @Autowired
    PedidosHasProductosBusiness pedidosHasProductosBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<PedidosHasProductosDTO>> getAllPedidosHasProductos() {
        List<PedidosHasProductosDTO> pedidosHasProductosList = pedidosHasProductosBusiness.findAll();
        if (pedidosHasProductosList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pedidosHasProductosList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPedidosHasProductosById(@PathVariable Long id) {
        try {
            PedidosHasProductosDTO pedidosHasProductos = pedidosHasProductosBusiness.getById(id);
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
    public ResponseEntity<Map<String, Object>> createPedidosHasProductos(@Validated @RequestBody PedidosHasProductosDTO pedidosHasProductosDto) {
        try {
            pedidosHasProductosBusiness.create(pedidosHasProductosDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Orders Has Products Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updatePedidosHasProductos(@PathVariable Long id, @Validated @RequestBody PedidosHasProductosDTO pedidosHasProductosDto) {
        try {
            pedidosHasProductosBusiness.update(id, pedidosHasProductosDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Orders Has Products Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePedidosHasProductos(@PathVariable Long id) {
        try {
            pedidosHasProductosBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Orders Has Products Deleted Successfully");
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