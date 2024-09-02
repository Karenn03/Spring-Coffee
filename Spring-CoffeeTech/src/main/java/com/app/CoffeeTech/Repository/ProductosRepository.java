package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.ProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<ProductosEntity, Long> {

}