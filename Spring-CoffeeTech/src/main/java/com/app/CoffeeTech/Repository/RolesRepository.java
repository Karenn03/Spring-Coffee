package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    RolesEntity findByRolName(String nombreRol);
}