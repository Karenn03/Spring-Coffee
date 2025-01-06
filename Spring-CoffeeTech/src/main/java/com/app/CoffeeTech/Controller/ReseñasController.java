package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.ReseñasBusiness;
import com.app.CoffeeTech.DTO.ReseñasDTO;
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
@RequestMapping("/api/reseñas")
public class ReseñasController {

    @Autowired
    ReseñasBusiness reseñasBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<ReseñasDTO>> getAllReseñas() {
        List<ReseñasDTO> reseñasList = reseñasBusiness.findAll();
        if (reseñasList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(reseñasList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getReseñaById(@PathVariable Long id) {
        try {
            ReseñasDTO reseñas = reseñasBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", reseñas);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createReseña(@Validated @RequestBody ReseñasDTO reseñasDto) {
        try {
            reseñasBusiness.create(reseñasDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Review Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateReseña(@PathVariable Long id, @Validated @RequestBody ReseñasDTO reseñasDto) {
        try {
            reseñasBusiness.update(id, reseñasDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Review Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteReseña(@PathVariable Long id) {
        try {
            reseñasBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Review Deleted Successfully");
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