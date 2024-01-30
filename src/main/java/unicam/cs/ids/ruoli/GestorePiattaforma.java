package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

import java.util.Set;

/**
 * Classe utilizzata per gestire la piattaforma.
 */
public class GestorePiattaforma {

    private final GestoreUtenti gestoreUtenti;
    private final GestoreComuni gestoreComuni;

    public GestorePiattaforma() {
        gestoreUtenti = new GestoreUtenti();
        gestoreComuni = new GestoreComuni();
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
                gestoreUtenti.toString() +
                ", " + gestoreComuni.toString() +
                '}';
    }

    public Set<Comune> getComuni() {
        return gestoreComuni.getComuni();
    }

}
