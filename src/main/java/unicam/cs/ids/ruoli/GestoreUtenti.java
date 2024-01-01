package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestoreUtenti {

    private GestoreRuoliSingleton gestoreRuoliSingleton;

    private Set<Utente> utenti;

    public GestoreUtenti() {
        gestoreRuoliSingleton = GestoreRuoliSingleton.getInstance();
        utenti = new HashSet<>();
    }

    public boolean aggiungiUtente(Utente utente) {
        return utenti.add(utente);
    }

    public boolean rimuoviUtente(Utente utente) {
        return utenti.remove(utente);
    }

    /**
     * Restituisce la lista dei comuni a cui un utente è abilitato.
     * @param utente l'utente di cui si vogliono conoscere i comuni abilitati.
     * @param ruolo il ruolo dell'utente.
     * @return la lista dei comuni a cui l'utente è abilitato con quel ruolo.
     */
    public List<Comune> getComuniAbilitati(Utente utente, Ruolo ruolo) {
        return gestoreRuoliSingleton.getComuniAbilitati(utente, ruolo);
    }

}
