package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.CarritoComprasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoComprasRepository extends JpaRepository<CarritoComprasEntity, Long> {

}