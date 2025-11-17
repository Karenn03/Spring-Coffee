package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.PrivilegiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegiosRepository extends JpaRepository<PrivilegiosEntity, Long> {
    PrivilegiosEntity findByNombrePrivilegio(String nombrePrivilegio);
}
