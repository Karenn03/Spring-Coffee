package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.DomicilioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends JpaRepository<DomicilioEntity, Long> {

}