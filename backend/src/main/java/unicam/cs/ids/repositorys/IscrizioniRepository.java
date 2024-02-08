package unicam.cs.ids.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.cs.ids.models.punti.Iscrizione;
import unicam.cs.ids.models.punti.IscrizioneKey;

public interface IscrizioniRepository extends JpaRepository<Iscrizione, IscrizioneKey> {

}
