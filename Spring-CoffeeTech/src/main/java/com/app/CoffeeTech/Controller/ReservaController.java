package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.ReservaBusiness;
import com.app.CoffeeTech.DTO.ReservaDTO;
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
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    ReservaBusiness reservaBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        List<ReservaDTO> reservasList = reservaBusiness.findAll();
        if (reservasList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(reservasList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getReservaById(@PathVariable Long id) {
        try {
            ReservaDTO reserva = reservaBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", reserva);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createReserva(@Validated @RequestBody ReservaDTO reservaDto) {
        try {
            reservaBusiness.create(reservaDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Reservation Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateReserva(@PathVariable Long id, @Validated @RequestBody ReservaDTO reservaDto) {
        try {
            reservaBusiness.update(id, reservaDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Reservation Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteReserva(@PathVariable Long id) {
        try {
            reservaBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Reservation Deleted Successfully");
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