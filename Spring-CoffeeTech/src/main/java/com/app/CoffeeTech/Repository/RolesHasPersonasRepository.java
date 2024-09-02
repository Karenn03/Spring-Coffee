package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.RolesHasPersonasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesHasPersonasRepository extends JpaRepository<RolesHasPersonasEntity, Long> {

}