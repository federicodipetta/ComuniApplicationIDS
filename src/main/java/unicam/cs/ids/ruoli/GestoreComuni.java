package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilizzata per gestire i comuni della piattaforma.
 */
public class GestoreComuni {

    private final List<Comune> comuni;

    public GestoreComuni() {
        this.comuni = new ArrayList<>();
    }

    /**
     * Restituisce la lista dei comuni
     * @return lista dei comuni
     */
    public List<Comune> getComuni() {
        return comuni;
    }

    /**
     * Restituisce il comune con l'id passato come parametro
     * @param idComune l'id del comune
     * @return il comune con l'id passato come parametro
     */
    public Comune getComune(String idComune) {
        for (Comune c : comuni) {
            if (c.id().equals(idComune)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Aggiunge un comune alla lista dei comuni
     * @param comune il comune da aggiungere
     * @return true se il comune è stato aggiunto, false altrimenti
     */
    public boolean aggiungiComune(Comune comune) {
        return comuni.add(comune);
    }

    @Override
    public String toString() {
        return "GestoreComuni{" +
                "comuni=" + comuni.size() +
                '}';
    }


}
