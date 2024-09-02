package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.VentasHasProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasHasProductosRepository extends JpaRepository<VentasHasProductosEntity, Long> {

}