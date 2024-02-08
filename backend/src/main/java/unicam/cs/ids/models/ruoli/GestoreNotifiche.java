package unicam.cs.ids.models.ruoli;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class GestoreNotifiche {

    // TODO: le notifiche non sono persistite.

    private final Map<Utente, Set<Notifica>> mappaUtentiNotifiche;

    public GestoreNotifiche() {
        mappaUtentiNotifiche = new HashMap<>();
    }

    /**
     * Metodo usato per ottenere le notifiche di un utente.
     * @param utente l'utente di cui si vogliono ottenere le notifiche.
     * @return Un Set contenente le notifiche dell'utente.
     */
    public Set<Notifica> getNotifiche(Utente utente) {
        return mappaUtentiNotifiche.get(utente);
    }

    /**
     * Metodo usato per aggiungere una notifica a un utente.
     * @param utente l'utente a cui si vuole aggiungere la notifica.
     * @param notifica la notifica da aggiungere.
     * @return true se la notifica è stata aggiunta, false altrimenti.
     */
    public boolean invioNotifica(Utente utente, Notifica notifica) {
        if(!mappaUtentiNotifiche.containsKey(utente)) {
            mappaUtentiNotifiche.put(utente, Set.of(notifica));
            return true;
        } else {
            return mappaUtentiNotifiche.get(utente).add(notifica);
        }
    }

    /**
     * Metodo usato per aggiungere una notifica a più utenti.
     * @param utenti la lista di utenti a cui si vuole aggiungere la notifica.
     * @param notifica la notifica da aggiungere.
     * @return true se la notifica è stata aggiunta a tutti gli utenti, false altrimenti.
     */
    public boolean invioNotifica(List<Utente> utenti, Notifica notifica) {
        return utenti
                .stream()
                .parallel()
                .allMatch(utente -> invioNotifica(utente, notifica));
    }

    /**
     * Metodo usato per rimuovere una notifica da un utente.
     *
     * @param utente     l'utente a cui si vuole rimuovere la notifica.
     * @param idNotifica la notifica da rimuovere.
     * @return true se la notifica è stata rimossa, false altrimenti.
     */
    public boolean rimuoviNotifica(Utente utente, String idNotifica) {
        if(!mappaUtentiNotifiche.containsKey(utente)) {
            return false;
        } else {
            return mappaUtentiNotifiche.get(utente).remove(idNotifica);
        }
    }

}
