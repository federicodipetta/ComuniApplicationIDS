package unicam.cs.ids.ruoli;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe utilizzata per gestire gli utenti della piattaforma.
 * Per ottenere i comuni a cui è abilitato un utente utilizza il GestoreRuoliSingleton.
 */
public class GestoreUtenti {

    private final GestoreRuoli gestoreRuoli;

    private final Set<Utente> utenti;

    public GestoreUtenti() {
        gestoreRuoli = new GestoreRuoli();
        utenti = new HashSet<>();
    }

    /**
     * Aggiunge un utente alla lista degli utenti.
     * @param utente l'utente da aggiungere.
     * @return true se l'utente è stato aggiunto, false altrimenti.
     */
    public boolean aggiungiUtente(Utente utente) {
        return utenti.add(utente);
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
        for(Utente utente : utenti) {
            if(utente.id().equals(idUtente)) {
                return utente;
            }
        }
        return null;
    }

}
