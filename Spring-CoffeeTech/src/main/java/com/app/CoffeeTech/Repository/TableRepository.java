package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableEntity,Long> {
    TableEntity findByNumero(Long numero);
}