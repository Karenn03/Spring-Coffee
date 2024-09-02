package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.VentasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository extends JpaRepository<VentasEntity, Long> {

}