package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<PedidosEntity, Long> {

}