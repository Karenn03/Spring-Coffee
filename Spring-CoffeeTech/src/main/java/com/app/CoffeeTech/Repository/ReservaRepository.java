package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

}