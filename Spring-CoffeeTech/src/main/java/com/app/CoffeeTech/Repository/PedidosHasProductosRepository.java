package Repository;

import Entity.PedidosHasProductosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosHasProductosRepository extends JpaRepository<PedidosHasProductosEntity, Long> {

}