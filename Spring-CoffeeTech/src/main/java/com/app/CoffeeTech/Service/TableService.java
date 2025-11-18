package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.TableEntity;
import com.app.CoffeeTech.Repository.TableRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class TableService implements IDAO<TableEntity, Long> {

    @Autowired
    TableRepository tableRepository;

    @Override
    public Page<TableEntity> findAll(PageRequest pageRequest) {
        return tableRepository.findAll(pageRequest);
    }

    @Override
    public TableEntity getById(Long id) {
        Optional<TableEntity> optionalTable = tableRepository.findById(id);
        return optionalTable.orElse(null);
    }

    @Override
    public void update(TableEntity entity) {
        this.tableRepository.save(entity);
    }

    @Override
    public TableEntity save(TableEntity entity) {
        return this.tableRepository.save(entity);
    }

    @Override
    public void delete(TableEntity entity) {
        this.tableRepository.delete(entity);
    }

    @Override
    public void create(TableEntity entity) {
        this.tableRepository.save(entity);
    }

    public TableEntity findByNumber(Long numero) {
        return tableRepository.findByNumero(numero);
    }

}