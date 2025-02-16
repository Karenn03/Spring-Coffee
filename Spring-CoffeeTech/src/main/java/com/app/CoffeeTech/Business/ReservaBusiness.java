package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.ReservaDTO;
import com.app.CoffeeTech.Entity.ReservaEntity;
import com.app.CoffeeTech.Service.ReservaService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ReservaBusiness {

    @Autowired
    ReservaService reservaService;

    // ModelMapper instance to convert to DTO (This only works if the attribute names are the same in the entity and the DTO)
    private final ModelMapper modelMapper = new ModelMapper();

    // Find All Reservations
    public Page<ReservaDTO> findAll(Pageable pageable) {
        try {
            PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
            Page<ReservaEntity> reservaList = reservaService.findAll(pageRequest);
            if (reservaList.isEmpty()){
                return Page.empty();
            }
            return reservaList.map(reservaEntity -> modelMapper.map(reservaEntity, ReservaDTO.class));
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las reservas." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find Reservation by ID
    public ReservaDTO findById(Long id){
        try {
            ReservaEntity reservaEntity = reservaService.getById(id);
            if (reservaEntity == null){
                throw new CustomException("Reserva con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            return modelMapper.map(reservaEntity, ReservaDTO.class);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error al obtener la reserva por id." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Add Reservation
    public void add(ReservaDTO reservaDto){
        try {
            ReservaEntity reservaEntity = modelMapper.map(reservaDto, ReservaEntity.class);
            reservaService.save(reservaEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e){
            throw new CustomException("Error creando la reserva." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Reservation
    public void update(Long id, ReservaDTO reservaDto) {
        try {
            ReservaEntity existingReservation = reservaService.getById(id);
            if (existingReservation == null) {
                throw new CustomException("Reserva con id " + id + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            existingReservation.setFecha(reservaDto.getFecha());
            reservaService.save(existingReservation);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la reserva." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete Reservation
    public void delete(Long idReserva) {
        try {
            ReservaEntity reservaEntity = reservaService.getById(idReserva);
            if (reservaEntity == null) {
                throw new CustomException("Reserva con id " + idReserva + " no encontrada.", HttpStatus.NOT_FOUND);
            }
            reservaService.delete(reservaEntity);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Error eliminando la reserva: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}