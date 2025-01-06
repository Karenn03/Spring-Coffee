package com.app.CoffeeTech.Business;

import com.app.CoffeeTech.DTO.MesasDTO;
import com.app.CoffeeTech.Entity.MesasEntity;
import com.app.CoffeeTech.Service.MesasService;
import com.app.CoffeeTech.Utilities.Exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MesasBusiness {

    @Autowired
    MesasService mesasService;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<MesasDTO> findAll(){
        try {
            List<MesasEntity> mesasList = mesasService.findAll();
            if (mesasList.isEmpty()){
                return new ArrayList<>();
            }
            List<MesasDTO> mesasDtoList = new ArrayList<>();
            mesasList.forEach(MesasEntity -> mesasDtoList.add(modelMapper.map(MesasEntity, MesasDTO.class)));
            return mesasDtoList;
        } catch (Exception e){
            throw new CustomException("Error al obtener todas las mesas.");
        }
    }

    public MesasDTO getById(Long id){
        try {
            MesasEntity mesasEntity = mesasService.getById(id);
            if (mesasEntity == null){
                throw new CustomException("Mesa con id " + id + " no encontrada.");
            }
            return modelMapper.map(mesasEntity, MesasDTO.class);
        } catch (Exception e){
            throw new CustomException("Error al obtener la mesa por id.");
        }
    }

    public void update(Long id, MesasDTO mesasDto) {
        try {
            MesasEntity existingTable = mesasService.getById(id);
            if (existingTable == null) {
                throw new CustomException("Mesa con id " + id + " no se encuentra.");
            }
            existingTable.setCapacidad(mesasDto.getCapacidad());
            mesasService.save(existingTable);
        } catch (Exception e) {
            throw new CustomException("Error al actualizar la mesa.");
        }
    }

    public void create(MesasDTO mesasDto){
        try {
            MesasEntity mesasEntity = modelMapper.map(mesasDto, MesasEntity.class);
            mesasService.save(mesasEntity);
        } catch (Exception e){
            throw new CustomException("Error creando la mesa.");
        }
    }

    public void delete(Long idMesa) {
        try {
            MesasEntity mesasEntity = mesasService.getById(idMesa);
            if (mesasEntity == null) {
                throw new CustomException("Mesa con id " + idMesa + " no encontrada.");
            }
            mesasService.delete(mesasEntity);
        } catch (Exception e) {
            throw new CustomException("Error eliminando la mesa: " + e.getMessage());
        }
    }
}