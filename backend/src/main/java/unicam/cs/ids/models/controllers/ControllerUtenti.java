package unicam.cs.ids.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.ruoli.*;

import java.util.Set;

@Service
public class ControllerUtenti {

    private final GestoreUtenti gestoreUtenti;

    private final GestoreNotifiche gestoreNotifiche;

    public ControllerUtenti() {
        this.gestoreUtenti = GestorePiattaforma.getInstance().getGestoreUtenti();
        this.gestoreNotifiche = new GestoreNotifiche();
    }

    /**
     * Costruisce un ControllerUtenti.
     * @param gestoreUtenti Il gestore degli utenti.
     * @param gestoreNotifiche Il gestore delle notifiche.
     */
    @Autowired
    public ControllerUtenti(GestoreUtenti gestoreUtenti, GestoreNotifiche gestoreNotifiche) {
        this.gestoreUtenti = gestoreUtenti;
        this.gestoreNotifiche = gestoreNotifiche;
    }

    public boolean setRuoloUtente(String idUtente, RuoloComune ruoloComune) {
        return gestoreUtenti.setRuoloUtente(gestoreUtenti.getUtenteById(idUtente), ruoloComune);
    }

    /**
     * Metodo utilizzato per aggiungere un utente al controller.
     * @param utente l'utente da aggiungere.
     * @return true se l'utente è stato aggiunto, false altrimenti.
     */
    public boolean aggiungiUtente(Utente utente) {
        return gestoreUtenti.aggiungiUtente(utente);
    }

    /**
     * Metodo utilizzato per rimuovere un utente dal controller.
     * @param idUtente l'utente da rimuovere.
     * @return true se l'utente è stato rimosso, false altrimenti.
     */
    public boolean rimuoviUtente(String idUtente) {
        return gestoreUtenti.rimuoviUtente(gestoreUtenti.getUtenteById(idUtente));
    }

    /**
     * Metodo utilizzato per ottenere i comuni abilitati di un utente.
     * @param idUtente l'id dell'utente.
     * @param ruolo il ruolo dell'utente.
     * @return Un Set contenente i comuni abilitati dell'utente.
     */
    public Set<Comune> getComuniAbilitati(String idUtente, Ruolo ruolo) {
        return gestoreUtenti.getGestoreRuoli().getComuniAbilitati(gestoreUtenti.getUtenteById(idUtente), ruolo);
    }

    /**
     * Metodo utilizzato per inviare una notifica a un utente.
     * @param idUtente l'id dell'utente.
     * @param notifica la notifica da inviare.
     * @return true se la notifica è stata inviata, false altrimenti.
     */
    public boolean invioNotifica(String idUtente, Notifica notifica) {
    	return gestoreNotifiche.invioNotifica(gestoreUtenti.getUtenteById(idUtente), notifica);
    }

    /**
     * Metodo utilizzato per ottenere le notifiche di un utente.
     * @param idUtente l'id dell'utente.
     * @return Un Set contenente le notifiche dell'utente.
     */
    public Set<Notifica> getNotifiche(String idUtente) {
    	return gestoreNotifiche.getNotifiche(gestoreUtenti.getUtenteById(idUtente));
    }

    /**
     * Metodo utilizzato per rimuovere una notifica di un utente.
     *
     * @param idUtente   l'id dell'utente.
     * @param idNotifica la notifica da rimuovere.
     * @return true se la notifica è stata rimossa, false altrimenti.
     */
    public boolean rimuoviNotifica(String idUtente, String idNotifica) {
    	return gestoreNotifiche.rimuoviNotifica(gestoreUtenti.getUtenteById(idUtente), idNotifica);
    }

    public boolean setRuoloUtente(String idUtente, String idComune, String ruolo) {
        Comune comune = GestorePiattaforma.getInstance().getGestoreComuni().getComuneById(idComune);
        Utente utente = gestoreUtenti.getUtenteById(idUtente);
        Ruolo ruoloUtente = Ruolo.getRuolo(ruolo);
        if(comune == null || utente == null || ruoloUtente == null) return false;
        return gestoreUtenti.setRuoloUtente(utente, new RuoloComune(comune, ruoloUtente));
    }

}
