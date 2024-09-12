package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.ReservaDTO;
import com.app.CoffeeTech.Entity.ReservaEntity;
import com.app.CoffeeTech.Service.ReservaService;
import com.app.CoffeeTech.Utilities.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservaBusiness {

    @Autowired
    ReservaService reservaService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<ReservaDTO> findAll(){
        try {
            List<ReservaEntity> reservaList = reservaService.findAll();
            if (reservaList.isEmpty()){
                return new ArrayList<>();
            }
            List<ReservaDTO> reservaDtoList = new ArrayList<>();
            reservaList.forEach(ReservaEntity -> reservaDtoList.add(modelMapper.map(ReservaEntity, ReservaDTO.class)));
            return reservaDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las reservas.");
        }
    }

    public ReservaDTO getById(Long id){
        try {
            ReservaEntity reservaEntity = reservaService.getById(id);
            if (reservaEntity == null){
                throw new CustomException("Reserva con id " + id + " no encontrada.");
            }
            return modelMapper.map(reservaEntity, ReservaDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener la reserva por id.");
        }
    }

    public void update(Long id, ReservaDTO reservaDto) {
        try {
            ReservaEntity existingReservation = reservaService.getById(id);
            if (existingReservation == null) {
                throw new CustomException("Reserva con id " + id + " no se encuentra.");
            }
            existingReservation.setFecha(reservaDto.getFecha());
            reservaService.save(existingReservation);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la reserva.");
        }
    }

    public void create(ReservaDTO reservaDto){
        try {
            ReservaEntity reservaEntity = modelMapper.map(reservaDto, ReservaEntity.class);
            reservaService.save(reservaEntity);
        } catch (Exception e){
            throw new CustomException("Error creando la reserva.");
        }
    }

    public void delete(Long idReserva) {
        try {
            ReservaEntity reservaEntity = reservaService.getById(idReserva);
            if (reservaEntity == null) {
                throw new CustomException("Reserva con id " + idReserva + " no encontrada.");
            }
            reservaService.delete(reservaEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando la reserva: " + e.getMessage());
        }
    }
}