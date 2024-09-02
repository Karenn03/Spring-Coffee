package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.ProductosHasCarritoComprasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosHasCarritoComprasRepository extends JpaRepository<ProductosHasCarritoComprasEntity, Long> {

}