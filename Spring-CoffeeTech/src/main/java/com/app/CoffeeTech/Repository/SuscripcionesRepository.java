package Repository;

import Entity.SuscripcionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuscripcionesRepository extends JpaRepository<SuscripcionesEntity, Long> {

}