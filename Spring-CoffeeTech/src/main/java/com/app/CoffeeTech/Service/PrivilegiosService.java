package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.PrivilegiosEntity;
import com.app.CoffeeTech.Repository.PrivilegiosRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrivilegiosService implements IDAO<PrivilegiosEntity, Long> {

    @Autowired
    PrivilegiosRepository privilegiosRepository;

    @Override
    public Page<PrivilegiosEntity> findAll(PageRequest pageRequest) {
        return privilegiosRepository.findAll(pageRequest);
    }

    @Override
    public PrivilegiosEntity getById(Long id) {
        Optional<PrivilegiosEntity> optionalPrivileges = privilegiosRepository.findById(id);
        return optionalPrivileges.orElse(null);
    }

    @Override
    public void update(PrivilegiosEntity entity) {
        this.privilegiosRepository.save(entity);
    }

    @Override
    public PrivilegiosEntity save(PrivilegiosEntity entity) {
        return this.privilegiosRepository.save(entity);
    }

    @Override
    public void delete(PrivilegiosEntity entity) {
        privilegiosRepository.delete(entity);
    }

    @Override
    public void create(PrivilegiosEntity entity) {
        this.privilegiosRepository.save(entity);
    }

    public PrivilegiosEntity findByNombrePrivilegio(String nombrePrivilegio) {
        return privilegiosRepository.findByNombrePrivilegio(nombrePrivilegio);
    }

}
