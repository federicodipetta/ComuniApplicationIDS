package unicam.cs.ids.models.ruoli;

import unicam.cs.ids.repositorys.UtentiRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe utilizzata per gestire gli utenti della piattaforma.
 * Per ottenere i comuni a cui è abilitato un utente utilizza il GestoreRuoliSingleton.
 */
public class GestoreUtenti {

    private final GestoreRuoli gestoreRuoli;

    private final Set<Utente> utenti;

    private UtentiRepository utentiRepository;

    public GestoreUtenti(UtentiRepository utentiRepository) {
        gestoreRuoli = new GestoreRuoli();
        utenti = new HashSet<>();
    }

    /**
     * Aggiunge un utente alla lista degli utenti.
     * @param utente l'utente da aggiungere.
     * @return true se l'utente è stato aggiunto, false altrimenti.
     */
    public boolean aggiungiUtente(Utente utente) {
        this.utentiRepository.save(utente);
        return true;
    }

    /**
     * Rimuove un utente dalla lista degli utenti.
     * @param utente l'utente da rimuovere.
     * @return true se l'utente è stato rimosso, false altrimenti.
     */
    public boolean rimuoviUtente(Utente utente) {
        this.utentiRepository.delete(utente);
        return true;
    }

    /**
     * Associa a un utente la coppia ruolo-comune.
     * @param utente l'utente a cui si vuole associare il ruolo.
     * @param ruoloComune La coppia ruolo-comune.
     * @return true se l'utente è stato associato al ruolo nel comune, false altrimenti.
     */
    public boolean setRuoloUtente(Utente utente, RuoloComune ruoloComune) {
    	return gestoreRuoli.setRuoloUtente(utente, ruoloComune);
    }

    @Override
    public String toString() {
        return "GestoreUtenti{" +
                "utenti=" + utenti.size() +
                '}';
    }

    public GestoreRuoli getGestoreRuoli() {
    	return gestoreRuoli;
    }

    public Utente getUtenteById(String idUtente) {
        return utentiRepository.getReferenceById(idUtente);
    }

}
