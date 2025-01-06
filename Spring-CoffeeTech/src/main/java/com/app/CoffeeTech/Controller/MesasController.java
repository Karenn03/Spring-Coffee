package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.MesasBusiness;
import com.app.CoffeeTech.DTO.MesasDTO;
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
@RequestMapping("/api/mesas")
public class MesasController {

    @Autowired
    MesasBusiness mesasBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<MesasDTO>> getAllMesas() {
        List<MesasDTO> mesasList = mesasBusiness.findAll();
        if (mesasList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mesasList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getMesaById(@PathVariable Long id) {
        try {
            MesasDTO mesa = mesasBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", mesa);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createMesa(@Validated @RequestBody MesasDTO mesasDto) {
        try {
            mesasBusiness.create(mesasDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Table Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMesa(@PathVariable Long id, @Validated @RequestBody MesasDTO mesasDto) {
        try {
            mesasBusiness.update(id, mesasDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Table Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteMesa(@PathVariable Long id) {
        try {
            mesasBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Table Deleted Successfully");
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