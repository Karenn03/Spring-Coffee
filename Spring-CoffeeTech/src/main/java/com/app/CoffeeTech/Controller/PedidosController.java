package com.app.CoffeeTech.Controller;

import com.app.CoffeeTech.Business.PedidosBusiness;
import com.app.CoffeeTech.DTO.PedidosDTO;
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
@RequestMapping("/api/pedidos")
public class PedidosController {

    @Autowired
    PedidosBusiness pedidosBusiness;

    // FInd All Orders
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll(Pageable pageable) {
        try {
            Page<PedidosDTO> pedidosDTOPage = pedidosBusiness.findAll(pageable);
            if (pedidosDTOPage.hasContent()) {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                pedidosDTOPage.getContent(),
                                ResponseHttpApi.CODE_OK,
                                "Orders found successfully.",
                                pedidosDTOPage.getTotalPages(),
                                pedidosDTOPage.getNumber(),
                                (int) pedidosDTOPage.getTotalElements()
                        ), HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        ResponseHttpApi.responseHttpFindAll(
                                null,
                                ResponseHttpApi.NO_CONTENT,
                                "No Orders found.",
                                0, 0, 0
                        ), HttpStatus.NO_CONTENT
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpError(
                            "Error retrieving Orders.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Find Order by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        try {
            PedidosDTO pedidosDTO = pedidosBusiness.findById(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpFindId(
                            pedidosDTO,
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
                            "Error getting Order: " + e.getMessage()
                    ), HttpStatus.CONFLICT
            );
        }
    }

    // Add Order
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@Validated @RequestBody PedidosDTO pedidosDTO) {
        try {
            pedidosBusiness.add(pedidosDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Order added successfully."
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
                            "Error creating Order.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Update Order
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Validated @RequestBody PedidosDTO pedidosDTO) {
        try {
            pedidosBusiness.update(id, pedidosDTO);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Order updated successfully."
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
                            "Error updating Order.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Delete Order
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        try {
            pedidosBusiness.delete(id);
            return new ResponseEntity<>(
                    ResponseHttpApi.responseHttpAction(
                            ResponseHttpApi.CODE_OK,
                            "Order deleted successfully."
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
                            "Error deleting Order.",
                            HttpStatus.INTERNAL_SERVER_ERROR),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}