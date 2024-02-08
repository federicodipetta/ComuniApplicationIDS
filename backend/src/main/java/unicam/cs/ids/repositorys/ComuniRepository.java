package unicam.cs.ids.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.cs.ids.models.Comune;

public interface ComuniRepository extends JpaRepository<Comune, String> {
}
