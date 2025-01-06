package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.TipoProductoBusiness;
import com.app.CoffeeTech.DTO.TipoProductoDTO;
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
@RequestMapping("/api/tiposProducto")
public class TipoProductoController {

    @Autowired
    TipoProductoBusiness tipoProductoBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<TipoProductoDTO>> getAllTiposProducto() {
        List<TipoProductoDTO> tipoProductoList = tipoProductoBusiness.findAll();
        if (tipoProductoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(tipoProductoList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTipoProductoById(@PathVariable Long id) {
        try {
            TipoProductoDTO tipoProducto = tipoProductoBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", tipoProducto);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTipoProducto(@Validated @RequestBody TipoProductoDTO tipoProductoDto) {
        try {
            tipoProductoBusiness.create(tipoProductoDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Product Type Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateTipoProducto(@PathVariable Long id, @Validated @RequestBody TipoProductoDTO tipoProductoDto) {
        try {
            tipoProductoBusiness.update(id, tipoProductoDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product Type Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteTipoProducto(@PathVariable Long id) {
        try {
            tipoProductoBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product Type Deleted Successfully");
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