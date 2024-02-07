package unicam.cs.ids.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import unicam.cs.ids.models.punti.Contenuto;

public interface ContenutiRepository extends JpaRepository<Contenuto, String> {

}
