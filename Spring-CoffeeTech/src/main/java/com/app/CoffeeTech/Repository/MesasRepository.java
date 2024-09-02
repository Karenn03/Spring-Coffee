package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.MesasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesasRepository extends JpaRepository<MesasEntity,Long> {

}