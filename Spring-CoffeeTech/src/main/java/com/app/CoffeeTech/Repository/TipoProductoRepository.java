package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.TipoProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProductoEntity, Long> {

}