package unicam.cs.ids.models.ruoli;

import com.fasterxml.jackson.annotation.JsonView;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.view.View;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe utilizzata per gestire i comuni della piattaforma.
 */
public class GestoreComuni {

    private final Set<GestoreComunale> gestoriComunali;

    public GestoreComuni() {
        this.gestoriComunali = new HashSet<>();
    }

    /**
     * Aggiunge un gestore comunale alla lista dei gestori comunali
     * @param comune il comune del gestore comunale da aggiungere
     * @return true se il gestore comunale è stato aggiunto, false altrimenti
     */
    public boolean aggiungiComune(Comune comune) {
        return gestoriComunali.add(new GestoreComunale(comune));
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
        for (GestoreComunale gc : gestoriComunali) {
            if (gc.getComune().id().equals(idComune)) {
                return gc.getComune();
            }
        }
        return null;
    }

    /**
     * Restituisce la lista dei comuni gestiti.
     * @return la lista dei comuni gestiti.
     */
    public Set<Comune> getComuni() {
        return gestoriComunali.stream()
                .map(GestoreComunale::getComune)
                .collect(Collectors.toSet());
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
