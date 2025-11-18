package com.app.CoffeeTech.Service;

import com.app.CoffeeTech.Entity.UserEntity;
import com.app.CoffeeTech.Repository.UserRepository;
import com.app.CoffeeTech.Service.DAO.IDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class UserService implements IDAO<UserEntity, Long> {

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<UserEntity> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public UserEntity getById(Long id) {
        Optional<UserEntity> optionalPerson = userRepository.findById(id);// Usa Optional para manejar el posible valor nulo al buscar por ID
        return optionalPerson.orElse(null);
    }

    @Override
    public void update(UserEntity entity) {
        this.userRepository.save(entity);
    }

    @Override
    public UserEntity save(UserEntity entity) {
        return this.userRepository.save(entity);
    }

    @Override
    public void delete(UserEntity entity) {
        this.userRepository.delete(entity);
    }

    @Override
    public void create(UserEntity entity) {
        this.userRepository.save(entity);
    }

    public UserEntity findByDocument(Integer documento) {
        return userRepository.findByDocumento(documento);
    }

}