package unicam.cs.ids.models.ruoli;

import unicam.cs.ids.models.Comune;
import unicam.cs.ids.repositorys.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe utilizzata per gestire i comuni della piattaforma.
 */
public class GestoreComuni {

    private final Set<GestoreComunale> gestoriComunali;

    private ComuniRepository comuniRepository;

    private ContestRepository contestRepository;

    private PuntiFisiciRepository puntiFisiciRepository;

    private ContenutiRepository contenutiRepository;

    private RichiesteRepository richiesteRepository;


    public GestoreComuni(ComuniRepository comuniRepository, ContestRepository contestRepository, PuntiFisiciRepository puntiFisiciRepository
            , ContenutiRepository contenutiRepository, RichiesteRepository richiesteRepository) {
        this.contestRepository = contestRepository;
        this.contenutiRepository = contenutiRepository;
        this.comuniRepository = comuniRepository;
        this.puntiFisiciRepository = puntiFisiciRepository;
        this.richiesteRepository = richiesteRepository;
        this.gestoriComunali = new HashSet<>();
    }


    /**
     * Aggiunge un gestore comunale alla lista dei gestori comunali
     * @param comune il comune del gestore comunale da aggiungere
     * @return true se il gestore comunale è stato aggiunto, false altrimenti
     */
    public boolean aggiungiComune(Comune comune) {
        var a = this.puntiFisiciRepository.save(comune.puntoComune());
        comune = this.comuniRepository.save(comune);
        a.setIdc(comune.id());
        this.puntiFisiciRepository.save(a);
        return gestoriComunali.add(new GestoreComunale(comuniRepository, comune, contestRepository, puntiFisiciRepository, contenutiRepository, richiesteRepository));
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
        if (this.comuniRepository.existsById(idComune))
            return comuniRepository.getReferenceById(idComune);
        return null;
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

    /**
     * Restituisce la lista dei comuni gestiti.
     * @return la lista dei comuni gestiti.
     */
    public Set<Comune> getComuni() {
        return new HashSet<>(comuniRepository.findAll());
    }

    public Set<GestoreComunale> getGestoriComunali() {
        return gestoriComunali;
    }
}
