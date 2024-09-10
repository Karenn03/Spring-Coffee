package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.ProductosBusiness;
import com.app.CoffeeTech.DTO.ProductosDTO;
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
@RequestMapping("/api/productos")
public class ProductosController {

    @Autowired
    ProductosBusiness productosBusiness;

    @GetMapping("/all")
    public ResponseEntity<List<ProductosDTO>> getAllProductos() {
        List<ProductosDTO> productosList = productosBusiness.findAll();
        if (productosList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productosList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProductoById(@PathVariable Long id) {
        try {
            ProductosDTO producto = productosBusiness.getById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("data ", producto);
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createProducto(@Validated @RequestBody ProductosDTO productosDto) {
        try {
            productosBusiness.create(productosDto);
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "success");
            response.put("message ", "Product Created Successfully");
            response.put("code", 200);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateProducto(@PathVariable Long id, @Validated @RequestBody ProductosDTO productosDto) {
        try {
            productosBusiness.update(id, productosDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product Updated Successfully");
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return handleException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteProducto(@PathVariable Long id) {
        try {
            productosBusiness.delete(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product Deleted Successfully");
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