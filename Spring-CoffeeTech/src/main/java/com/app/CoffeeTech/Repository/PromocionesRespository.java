package Repository;

import Entity.PromocionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface PromocionesRespository extends JpaRepository<PromocionesEntity, Long> {

}