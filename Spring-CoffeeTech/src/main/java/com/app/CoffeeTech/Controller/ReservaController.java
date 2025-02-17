package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.ReservaBusiness;
import com.app.CoffeeTech.DTO.ReservaDTO;
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
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    ReservaBusiness reservaBusiness;

    // FInd All Reservation
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(Pageable pageable) {
        try {
            Page<ReservaDTO> reservaDTOPage = reservaBusiness.findAll(pageable);
            if (reservaDTOPage.hasContent()) {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                reservaDTOPage.getContent(),
                                ResponseHttpApi.CODE_OK,
                                "Reservation found successfully.",
                                reservaDTOPage.getTotalPages(),
                                reservaDTOPage.getNumber(),
                                (int) reservaDTOPage.getTotalElements()
                        ), HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                null,
                                ResponseHttpApi.NO_CONTENT,
                                "No Reservation found.",
                                0, 0, 0
                        ), HttpStatus.NO_CONTENT
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpError(
                            "Error retrieving Reservation.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Find Reservation by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        try {
            ReservaDTO reservaDTO = reservaBusiness.findById(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpFindId(
                            reservaDTO,
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
                            "Error getting Reservation: " + e.getMessage()
                    ), HttpStatus.CONFLICT
            );
        }
    }

    // Add Reservation
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@Validated @RequestBody ReservaDTO reservaDTO) {
        try {
            reservaBusiness.add(reservaDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Reservation added successfully."
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
                            "Error creating Reservation.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Update Reservation
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Validated @RequestBody ReservaDTO reservaDTO) {
        try {
            reservaBusiness.update(id, reservaDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Reservation updated successfully."
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
                            "Error updating Reservation.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Delete Reservation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        try {
            reservaBusiness.delete(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Reservation deleted successfully."
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
                            "Error deleting Reservation.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}