package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByDocumento(Integer documento);
    Optional<UserEntity> findByCorreoElectronico(String correoElectronico);
}