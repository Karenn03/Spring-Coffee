package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.VentasBusiness;
import com.app.CoffeeTech.DTO.VentasDTO;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import com.app.CoffeeTech.Utilities.Http.ResponseHttpApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {
    @Autowired
    VentasBusiness ventasBusiness;

    // FInd All Sales
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(Pageable pageable) {
        try {
            Page<VentasDTO> ventasDTOPage = ventasBusiness.findAll(pageable);
            if (ventasDTOPage.hasContent()) {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                ventasDTOPage.getContent(),
                                ResponseHttpApi.CODE_OK,
                                "Sales found successfully.",
                                ventasDTOPage.getTotalPages(),
                                ventasDTOPage.getNumber(),
                                (int) ventasDTOPage.getTotalElements()
                        ), HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                null,
                                ResponseHttpApi.NO_CONTENT,
                                "No Sales found.",
                                0, 0, 0
                        ), HttpStatus.NO_CONTENT
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpError(
                            "Error retrieving Sales.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Find Sale by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        try {
            VentasDTO ventasDTO = ventasBusiness.findById(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpFindId(
                            ventasDTO,
                            ResponseHttpApi.CODE_OK,
                            "Successfully completed."
                    ), HttpStatus.OK
            );
        } catch (CustomException e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpFindId(
                            null,
                            ResponseHttpApi.CODE_BAD,
                            e.getMessage()
                    ), HttpStatus.CONFLICT
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpFindId(
                            null,
                            ResponseHttpApi.CODE_BAD,
                            "Error getting Sale: " + e.getMessage()
                    ), HttpStatus.CONFLICT
            );
        }
    }

    // Add Sale
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@Validated @RequestBody VentasDTO ventasDTO) {
        try {
            ventasBusiness.add(ventasDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Sale added successfully."
                    ), HttpStatus.CREATED
            );
        } catch (CustomException e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_BAD,
                            e.getMessage()
                    ), HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpError(
                            "Error creating Sale.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Update Sale
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Validated @RequestBody VentasDTO ventasDTO) {
        try {
            ventasBusiness.update(id, ventasDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Sale updated successfully."
                    ), HttpStatus.OK
            );
        } catch (CustomException e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_BAD,
                            e.getMessage()
                    ), HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpError(
                            "Error updating Sale.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Delete Sale
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        try {
            ventasBusiness.delete(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Sale deleted successfully."
                    ), HttpStatus.OK
            );
        } catch (CustomException e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_BAD,
                            e.getMessage()
                    ), HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpError(
                            "Error deleting Sale.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}