package unicam.cs.ids.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import unicam.cs.ids.richieste.RichiestaCommand;
import unicam.cs.ids.richieste.RichiestaContenuto;
import unicam.cs.ids.richieste.RichiestaEliminaContenuto;
import unicam.cs.ids.richieste.Segnalazione;
import unicam.cs.ids.ruoli.GestoreComunale;
import unicam.cs.ids.ruoli.GestoreComuni;

public class ControllerRichieste {
    GestoreComuni gestoreComuni;

    /**
     * Costruisce un ControllerRichieste.
     * @param gestoreComuni Il gestore dei comuni.
     */
    public ControllerRichieste(GestoreComuni gestoreComuni) {
        this.gestoreComuni = gestoreComuni;
    }
    /**
     * aggiunge una richiesta di aggiunta di un contenuto
     * @param richiestaContenuto
     * @param idComune
     * @return true se la richiesta è stata aggiunta, false altrimenti.
     */
    public boolean aggiungiRichiestaAggiunta(RichiestaContenuto richiestaContenuto, String idComune){
        return true;
    }

    /**
     * restituisce le richieste di iscrizione ad un contest
     * @param utente animatore del contest
     * @return le richieste di iscrizione ad un contest in formato JSON
     */
    public JSONArray getRichiesteContest(String utente){
        return null;
    }

    /**
     * valuta una richiesta generica
     * @param richiestaCommand richiesta da valutare
     * @param ccettazione true se la richiesta è accettata, false altrimenti
     * @return true se la richiesta è stata valutata, false altrimenti.
     */
    public boolean valutaRichiesta(RichiestaCommand richiestaCommand, boolean ccettazione){
        return true;
    }

    /**
     * aggiunge una segnalazione
     * @param segnalazione segnalazione da aggiungere
     * @return true se la segnalazione è stata aggiunta, false altrimenti.
     */
    public boolean aggiungiSegnalazione(Segnalazione segnalazione){
        return true;
    }

    /**
     * aggiunge una richiesta di eliminazione di un contenuto
     * @param richiestaEliminaContenuto richiesta da aggiungere
     * @return true se la richiesta è stata aggiunta, false altrimenti.
     */
    public boolean aggiuntaRichiestaEliminazione(RichiestaEliminaContenuto richiestaEliminaContenuto){
        return true;
    }

    /**
     * la richiesta da aggiungere
     * @param richiestaCommand la richiesta
     * @param idComune il comune in cui aggiungerla
     * @return true se la richiesta è stata aggiunta, false altrimenti.
     * @throws IllegalArgumentException se non è una richiesta normale,
     *         quindi quelle legate al contest ad esempio.
     */
    public boolean aggiungiRichiesta(RichiestaCommand richiestaCommand, String idComune){
        return true;
    }

    /**
     * restituisce una richiesta
     * @param richiestaCommand richiesta da restituire
     * @return la richiesta in formato JSON
     */
    public JSONObject getRichiesta(String richiestaCommand){
        return null;
    }

}
