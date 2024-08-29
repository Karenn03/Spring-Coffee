package Repository;

import Entity.UsuarioHasRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioHasRolesRepository extends JpaRepository<UsuarioHasRolesEntity, Long> {

}