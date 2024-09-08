package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    PersonaEntity findByDocument(String documento);
}