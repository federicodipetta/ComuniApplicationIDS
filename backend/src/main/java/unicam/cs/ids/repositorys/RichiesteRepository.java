package unicam.cs.ids.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.cs.ids.models.richieste.RichiestaAstratta;

public interface RichiesteRepository extends JpaRepository<RichiestaAstratta, String> {
}
