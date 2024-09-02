package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.PromocionesHasProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionesHasProductosRepository extends JpaRepository<PromocionesHasProductosEntity, Long> {

}