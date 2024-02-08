package unicam.cs.ids.models.ruoli;


import org.json.JSONArray;
import org.json.JSONObject;
import unicam.cs.ids.models.richieste.RichiestaAstratta;
import unicam.cs.ids.models.richieste.RichiestaCommand;
import unicam.cs.ids.repositorys.RichiesteRepository;

import java.util.*;

public class GestoreRichieste {

    private static Set<RichiestaAstratta> richiesteBase;
    private static Map<Utente, RichiestaAstratta> richiesteIscrizione;

    private RichiesteRepository richiesteRepository;

    public GestoreRichieste (RichiesteRepository richiesteRepository) {
        richiesteBase = new HashSet<>();
        richiesteIscrizione = new HashMap<>();
    }

    /**
     * Questo metodo aggiunge una richiesta non legata a contest.
     * @param richiesta la richiesta da aggiungere.
     * @return true se la richiesta è stata aggiunta correttamente, false altrimenti.
     */
    public boolean aggiungiRichiesta(RichiestaAstratta richiesta) {
        this.richiesteRepository.save(richiesta);
        return true;
    }

    /**
     * Questo metodo aggiunge una richiesta legata a contest.
     * @param richiesta la richiesta da aggiungere.
     * @param utente l'animatore che ha la richiesta associata
     * @return true se la richiesta è stata aggiunta correttamente, false altrimenti.
     */
    public boolean aggiungiRichiestaContest(RichiestaAstratta richiesta, Utente utente) {
        // TODO: implementare il salvataggio della richiesta
        return richiesteIscrizione.put(utente,richiesta) == null;
    }

    /**
     * Questo metodo valuta una richiesta se presente nel gestore.
     * @param richiesta la richiesta da valutare.
     * @param valutazione la valutazione da assegnare alla richiesta.
     * @return true se la richiesta è stata valutata correttamente, false altrimenti.
     */
    public boolean valutaRichiesta(RichiestaAstratta richiesta, boolean valutazione) {
        this.richiesteRepository.getReferenceById(richiesta.getId()).esegui(valutazione);
        this.richiesteRepository.delete(richiesta);

        //TODO: implementare valutazione richieste iscrizione
        /*if(richiesteIscrizione.containsValue(richiesta)) {
            richiesta.esegui(valutazione);
            richiesteIscrizione.remove(richiesteIscrizione.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(richiesta))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null), richiesta);
            return true;
        }*/
        return true;
    }

    public RichiestaAstratta getRichiestaById(String id){
        return this.richiesteRepository.getReferenceById(id);
    }

    public JSONArray getDettagliRichiesteContest(Utente utente) {
        //TODO: implementare
        try {
            return new JSONArray(richiesteIscrizione.entrySet().stream()
                    .filter(entry -> entry.getKey().equals(utente))
                    .map(Map.Entry::getValue)
                    .map(RichiestaCommand::dettagliMinimi)
                    .toArray());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "GestoreRichieste{" +
                "richiesteBase=" + richiesteBase.size() +
                ", richiesteIscrizione=" + richiesteIscrizione.size() +
                '}';
    }

    /**
     * Questo metodo restituisce i dettagli di una richiesta legata a un contest.
     * @param richiesta la richiesta di cui si vogliono i dettagli.
     * @return i dettagli della richiesta o null s essa non è presente.
     */
    public JSONObject getDettagliRichiestaContest (RichiestaCommand richiesta) {
        // Sostituito con json view
        if (richiesteIscrizione.containsValue(richiesta)) {
            return richiesta.dettagli();
        }
        return null;
    }

    /**
     * Questo metodo restituisce i dettagli di una richiesta non legata a un contest.
     * @param richiesta la richiesta di cui si vogliono i dettagli.
     * @return i dettagli della richiesta o null se essa non è presente.
     */
    public JSONObject getDettagliRichiesta (RichiestaCommand richiesta) {
        // Sostituito dalle json view.
        return null;
    }

    public Collection<RichiestaAstratta> getRichieste() {
        return this.richiesteRepository.findAll();
    }



}
