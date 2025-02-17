package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.PersonaBusiness;
import com.app.CoffeeTech.DTO.PersonaDTO;
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
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    PersonaBusiness personaBusiness;

    // FInd All People
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(Pageable pageable) {
        try {
            Page<PersonaDTO> personaDTOPage = personaBusiness.findAll(pageable);
            if (personaDTOPage.hasContent()) {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                personaDTOPage.getContent(),
                                ResponseHttpApi.CODE_OK,
                                "People found successfully.",
                                personaDTOPage.getTotalPages(),
                                personaDTOPage.getNumber(),
                                (int) personaDTOPage.getTotalElements()
                        ), HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                null,
                                ResponseHttpApi.NO_CONTENT,
                                "No People found.",
                                0, 0, 0
                        ), HttpStatus.NO_CONTENT
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpError(
                            "Error retrieving People.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Find Person by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        try {
            PersonaDTO personaDTO = personaBusiness.findById(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpFindId(
                            personaDTO,
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
                            "Error getting Person: " + e.getMessage()
                    ), HttpStatus.CONFLICT
            );
        }
    }

    // Add Person
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@Validated @RequestBody PersonaDTO personaDTO) {
        try {
            personaBusiness.add(personaDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Person added successfully."
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
                            "Error creating Person.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Update Person
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Validated @RequestBody PersonaDTO personaDTO) {
        try {
            personaBusiness.update(id, personaDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Person updated successfully."
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
                            "Error updating Person.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Delete Person
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        try {
            personaBusiness.delete(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Person deleted successfully."
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
                            "Error deleting Person.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}