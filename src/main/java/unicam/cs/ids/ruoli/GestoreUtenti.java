package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe utilizzata per gestire gli utenti della piattaforma.
 * Per ottenere i comuni a cui è abilitato un utente utilizza il GestoreRuoliSingleton.
 */
public class GestoreUtenti {

    private final GestoreRuoliSingleton gestoreRuoliSingleton;

    private final Set<Utente> utenti;

    public GestoreUtenti() {
        gestoreRuoliSingleton = GestoreRuoliSingleton.getInstance();
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
     * Rimuove un utente dalla lista degli utenti.
     * @param utente l'utente da rimuovere.
     * @return true se l'utente è stato rimosso, false altrimenti.
     */
    public boolean rimuoviUtente(Utente utente) {
        return utenti.remove(utente);
    }

    /**
     * Restituisce la lista dei comuni a cui un utente è abilitato.
     * @param utente l'utente di cui si vogliono conoscere i comuni abilitati.
     * @param ruolo il ruolo dell'utente.
     * @return la lista dei comuni a cui l'utente è abilitato con quel ruolo.
     */
    public Set<Comune> getComuniAbilitati(Utente utente, Ruolo ruolo) {
        return gestoreRuoliSingleton.getComuniAbilitati(utente, ruolo);
    }

    /**
     * Associa a un utente la coppia ruolo-comune.
     * @param utente l'utente a cui si vuole associare il ruolo.
     * @param ruoloComune La coppia ruolo-comune.
     * @return true se l'utente è stato associato al ruolo nel comune, false altrimenti.
     */
    public boolean setRuoloUtente(Utente utente, RuoloComune ruoloComune) {
    	return gestoreRuoliSingleton.aggiungiUtenteRuoloComune(utente, ruoloComune);
    }

    @Override
    public String toString() {
        return "GestoreUtenti{" +
                "utenti=" + utenti.size() +
                '}';
    }

}
