package unicam.cs.ids.models.ruoli;

import unicam.cs.ids.models.Comune;
import unicam.cs.ids.repositorys.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe utilizzata per gestire i comuni della piattaforma.
 */
public class GestoreComuni {

    private Set<GestoreComunale> gestoriComunali;

    private ComuniRepository comuniRepository;

    private ContestRepository contestRepository;

    private PuntiFisiciRepository puntiFisiciRepository;

    private ContenutiRepository contenutiRepository;

    private RichiesteRepository richiesteRepository;

    public GestoreComuni(ComuniRepository comuniRepository, ContestRepository contestRepository, PuntiFisiciRepository puntiFisiciRepository, ContenutiRepository contenutiRepository,
                         RichiesteRepository richiesteRepository) {
        this.contestRepository = contestRepository;
        this.contenutiRepository = contenutiRepository;
        this.comuniRepository = comuniRepository;
        this.puntiFisiciRepository = puntiFisiciRepository;
        this.gestoriComunali = new HashSet<>();
        this.richiesteRepository = richiesteRepository;
    }

    /**
     * Aggiunge un gestore comunale alla lista dei gestori comunali
     * @param comune il comune del gestore comunale da aggiungere
     * @return true se il gestore comunale è stato aggiunto, false altrimenti
     */
    public boolean aggiungiComune(Comune comune) {
        this.puntiFisiciRepository.save(comune.puntoComune());
        Comune c = this.comuniRepository.save(comune);
        return gestoriComunali.add(new GestoreComunale(comuniRepository, c, contestRepository, puntiFisiciRepository, contenutiRepository, richiesteRepository));
    }

    @Override
    public String toString() {
        return "GestoreComuni{" +
                "GestoriComunali=" + gestoriComunali.size() +
                '}';
    }

    /**
     * Questo metodo restituisce un gestore comunale dato un id di comune.
     * @param idComune l'id del comune del gestore comunale da restituire.
     * @return il gestore comunale richiesto.
     */
    public Comune getComuneById(String idComune) {
        return comuniRepository.getReferenceById(idComune);
    }

    /**
     * Restituisce la lista dei comuni gestiti.
     * @return la lista dei comuni gestiti.
     */
    public Set<Comune> getComuni() {
        return new HashSet<>(comuniRepository.findAll());
    }

    /**
     * Restituisce un gestore comunale dato un comune
     * @param comune il comune del gestore comunale da restituire
     * @return il gestore comunale richiesto
     */
    public GestoreComunale getGestoreComunale(Comune comune) {
        return gestoriComunali.stream()
                .filter(gc -> gc.getComune().equals(comune))
                .findFirst()
                .orElse(null);
    }

    public Set<GestoreComunale> getGestoriComunali() {
        return gestoriComunali;
    }
}
