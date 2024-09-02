package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.ReseñasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseñasRepository extends JpaRepository<ReseñasEntity, Long> {

}