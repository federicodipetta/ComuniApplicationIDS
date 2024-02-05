package unicam.cs.ids.models.ruoli;

import unicam.cs.ids.models.Comune;

import java.util.Set;

/**
 * Classe utilizzata per gestire la piattaforma.
 */
public class GestorePiattaforma {

    private static GestorePiattaforma instance = null;

    private final GestoreUtenti gestoreUtenti;

    public GestoreComuni getGestoreComuni() {
        return gestoreComuni;
    }

    private final GestoreComuni gestoreComuni;

    private GestorePiattaforma() {
        gestoreUtenti = new GestoreUtenti();
        gestoreComuni = new GestoreComuni();
    }

    public static GestorePiattaforma getInstance() {
        if (instance == null) {
            instance = new GestorePiattaforma();
        }
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
