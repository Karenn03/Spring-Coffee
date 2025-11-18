package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.PrivilegeEntity;
import com.app.CoffeeTech.Repository.PrivilegeRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrivilegeService implements IDAO<PrivilegeEntity, Long> {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Override
    public Page<PrivilegeEntity> findAll(PageRequest pageRequest) {
        return privilegeRepository.findAll(pageRequest);
    }

    @Override
    public PrivilegeEntity getById(Long id) {
        Optional<PrivilegeEntity> optionalPrivileges = privilegeRepository.findById(id);
        return optionalPrivileges.orElse(null);
    }

    @Override
    public void update(PrivilegeEntity entity) {
        this.privilegeRepository.save(entity);
    }

    @Override
    public PrivilegeEntity save(PrivilegeEntity entity) {
        return this.privilegeRepository.save(entity);
    }

    @Override
    public void delete(PrivilegeEntity entity) {
        privilegeRepository.delete(entity);
    }

    @Override
    public void create(PrivilegeEntity entity) {
        this.privilegeRepository.save(entity);
    }

    public PrivilegeEntity findByNombrePrivilegio(String nombrePrivilegio) {
        return privilegeRepository.findByNombrePrivilegio(nombrePrivilegio);
    }

}
