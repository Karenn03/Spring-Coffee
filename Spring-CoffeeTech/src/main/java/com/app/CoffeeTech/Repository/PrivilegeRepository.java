package com.app.CoffeeTech.Repository;

import com.app.CoffeeTech.Entity.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, Long> {
    PrivilegeEntity findByNombrePrivilegio(String nombrePrivilegio);
}
