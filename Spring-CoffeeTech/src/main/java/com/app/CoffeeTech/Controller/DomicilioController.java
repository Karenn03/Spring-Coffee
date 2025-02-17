package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.DomicilioBusiness;
import com.app.CoffeeTech.DTO.DomicilioDTO;
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
@CrossOrigin("*")
@RequestMapping("/api/domicilio")
public class DomicilioController {

    @Autowired
    DomicilioBusiness domicilioBusiness;

    // FInd All Deliveries
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(Pageable pageable) {
        try {
            Page<DomicilioDTO> domicilioDTOPage = domicilioBusiness.findAll(pageable);
            if (domicilioDTOPage.hasContent()) {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                domicilioDTOPage.getContent(),
                                ResponseHttpApi.CODE_OK,
                                "Deliveries found successfully.",
                                domicilioDTOPage.getTotalPages(),
                                domicilioDTOPage.getNumber(),
                                (int) domicilioDTOPage.getTotalElements()
                        ), HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                null,
                                ResponseHttpApi.NO_CONTENT,
                                "No Deliveries found.",
                                0, 0, 0
                        ), HttpStatus.NO_CONTENT
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpError(
                            "Error retrieving Deliveries.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Find Delivery by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        try {
            DomicilioDTO domicilioDTO = domicilioBusiness.findById(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpFindId(
                            domicilioDTO,
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
                            "Error getting Delivery: " + e.getMessage()
                    ), HttpStatus.CONFLICT
            );
        }
    }

    // Add Delivery
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@Validated @RequestBody DomicilioDTO domicilioDTO) {
        try {
            domicilioBusiness.add(domicilioDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Delivery added successfully."
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
                            "Error creating Delivery.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Update Delivery
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Validated @RequestBody DomicilioDTO domicilioDTO) {
        try {
            domicilioBusiness.update(id, domicilioDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Delivery updated successfully."
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
                            "Error updating Delivery.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Delete Delivery
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        try {
            domicilioBusiness.delete(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Delivery deleted successfully."
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
                            "Error deleting Delivery.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}