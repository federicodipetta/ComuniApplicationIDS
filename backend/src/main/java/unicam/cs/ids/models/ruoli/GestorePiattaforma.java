package unicam.cs.ids.models.ruoli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.cs.ids.configurazioni.GestorePiattaformaBuilder;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.repositorys.*;

import java.util.Set;

/**
 * Classe utilizzata per gestire la piattaforma.
 */
@Service
public class GestorePiattaforma {

    private static GestorePiattaforma instance = null;

    private final GestoreUtenti gestoreUtenti;

    public GestoreComuni getGestoreComuni() {
        return gestoreComuni;
    }

    private final GestoreComuni gestoreComuni;

    ComuniRepository comuniRepository;
    RichiesteRepository richiesteRepository;
    ContenutiRepository contenutiRepository;
    ContestRepository contestRepository;
    PuntiFisiciRepository puntiFisiciRepository;
    UtentiRepository utentiRepository;

    @Autowired
    private GestorePiattaforma(GestorePiattaformaBuilder builder) {
        this.comuniRepository = builder.getComuniRepository();
        this.richiesteRepository = builder.getRichiesteRepository();
        this.contenutiRepository = builder.getContenutiRepository();
        this.contestRepository = builder.getContestRepository();
        this.puntiFisiciRepository = builder.getPuntiFisiciRepository();
        this.utentiRepository = builder.getUtentiRepository();
        this.gestoreUtenti = new GestoreUtenti();
        this.gestoreComuni = new GestoreComuni(comuniRepository, contestRepository, puntiFisiciRepository, contenutiRepository, richiesteRepository);
        this.instance = this;
    }

    public GestorePiattaforma() {
        this.gestoreUtenti = new GestoreUtenti();
        this.gestoreComuni = new GestoreComuni(comuniRepository, contestRepository, puntiFisiciRepository, contenutiRepository, richiesteRepository);
    }

    public static GestorePiattaforma getInstance() {
        return instance;
    }

    /**
     * Aggiunge un utente alla lista degli utenti
     * @param utente l'utente da aggiungere
     * @return true se l'utente è stato aggiunto, false altrimenti
     */
    public boolean aggiungiUtente(Utente utente) {
        return gestoreUtenti.aggiungiUtente(utente);
    }

    /**
     * Aggiunge un comune alla lista dei comuni
     * @param comune il comune da aggiungere
     * @return true se il comune è stato aggiunto, false altrimenti
     */
    public boolean aggiungiComune(Comune comune) {
        return gestoreComuni.aggiungiComune(comune);
    }

    @Override
    public String toString() {
        return "GestorePiattaforma{" +
                gestoreUtenti +
                ", " + gestoreComuni +
                '}';
    }

    public Set<Comune> getComuni() {
        return gestoreComuni.getComuni();
    }

    public GestoreUtenti getGestoreUtenti() {
        return gestoreUtenti;
    }

}
