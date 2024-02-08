package unicam.cs.ids.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.cs.ids.models.ruoli.Utente;

public interface UtentiRepository extends JpaRepository<Utente, String> {

}
