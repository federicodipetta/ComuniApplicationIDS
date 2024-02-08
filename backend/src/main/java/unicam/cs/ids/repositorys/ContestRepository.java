package unicam.cs.ids.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.cs.ids.models.punti.Contest;

public interface ContestRepository extends JpaRepository<Contest, String> {
}
