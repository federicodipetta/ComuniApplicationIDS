package unicam.cs.ids.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.cs.ids.models.punti.PuntoFisico;

public interface PuntiFisiciRepository extends JpaRepository<PuntoFisico, Long> {
}
