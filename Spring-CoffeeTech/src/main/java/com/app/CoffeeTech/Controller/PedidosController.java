package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.PedidosBusiness;
import com.app.CoffeeTech.DTO.PedidosDTO;
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
@RequestMapping("/api/pedidos")
public class PedidosController {

    @Autowired
    PedidosBusiness pedidosBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<PedidosDTO>> getAllPedidos() {
        List<PedidosDTO> pedidosList = pedidosBusiness.findAll();
        if (pedidosList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(pedidosList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPedidoById(@PathVariable Long id) {
        try {
            PedidosDTO pedido = pedidosBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", pedido);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPedido(@Validated @RequestBody PedidosDTO pedidoDto) {
        try {
            pedidosBusiness.create(pedidoDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Order Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updatePedido(@PathVariable Long id, @Validated @RequestBody PedidosDTO pedidosDto) {
        try {
            pedidosBusiness.update(id, pedidosDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Order Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deletePedido(@PathVariable Long id) {
        try {
            pedidosBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Order Deleted Successfully");
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