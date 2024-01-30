package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe utilizzata per gestire i comuni della piattaforma.
 */
public class GestoreComuni {

    private final Set<GestoreComunale> gestoriComunali;

    public GestoreComuni() {
        this.gestoriComunali = new HashSet<>();
    }

    /**
     * Restituisce un gestore comunale dato un comune
     * @param comune il comune del gestore comunale da restituire
     * @return il gestore comunale richiesto
     */
    public GestoreComunale getGestoreComunale(Comune comune) {
        for (GestoreComunale gc : gestoriComunali) {
            if (gc.getComune().equals(comune)) {
                return gc;
            }
        }
        return null;
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


    public Comune getComuneById(String idComune) {
            //TODO: implementare
        return null;
    }
}
