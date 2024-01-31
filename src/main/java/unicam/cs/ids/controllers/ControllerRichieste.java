package unicam.cs.ids.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import unicam.cs.ids.richieste.RichiestaCommand;
import unicam.cs.ids.richieste.RichiestaContenuto;
import unicam.cs.ids.richieste.RichiestaEliminaContenuto;
import unicam.cs.ids.richieste.Segnalazione;
import unicam.cs.ids.ruoli.*;

public class ControllerRichieste {
    GestoreComuni gestoreComuni;
    GestoreUtenti gestoreUtenti;
    /**
     * Costruisce un ControllerRichieste.
     * @param gestoreComuni Il gestore dei comuni.
     */
    public ControllerRichieste(GestoreComuni gestoreComuni, GestoreUtenti gestoreUtenti) {
        this.gestoreUtenti = gestoreUtenti;
        this.gestoreComuni = gestoreComuni;
    }
    /**
     * aggiunge una richiesta di aggiunta di un contenuto
     * @param richiestaContenuto
     * @param idComune
     * @return true se la richiesta è stata aggiunta, false altrimenti.
     */
    public boolean aggiungiRichiestaAggiunta(RichiestaContenuto richiestaContenuto, String idComune){
        return aggiungiRichiestaGenerica(richiestaContenuto, idComune);
    }

    /**
     * restituisce le richieste di iscrizione ad un contest
     * @param utente animatore del contest
     * @return le richieste di iscrizione ad un contest in formato JSON
     */
    public JSONArray getRichiesteContest(String utente){
        return gestoreComuni.getGestoriComunali().stream()
                .map(GestoreComunale::getGestoreRichieste)
                .map(x->x.getDettagliRichiesteContest(gestoreUtenti.getUtenteById(utente)))
                .collect(JSONArray::new, JSONArray::put, JSONArray::put);
    }

    /**
     * valuta una richiesta generica
     * @param richiestaCommand richiesta da valutare
     * @param accettazione true se la richiesta è accettata, false altrimenti
     * @return true se la richiesta è stata valutata, false altrimenti.
     */
    public boolean valutaRichiesta(RichiestaCommand richiestaCommand, boolean accettazione){
        return gestoreComuni.getGestoriComunali().stream()
                .map(GestoreComunale::getGestoreRichieste)
                .anyMatch(x->x.valutaRichiesta(richiestaCommand, accettazione));
    }

    /**
     * aggiunge una segnalazione
     * @param segnalazione segnalazione da aggiungere
     * @return true se la segnalazione è stata aggiunta, false altrimenti.
     */
    public boolean aggiungiSegnalazione(Segnalazione segnalazione, String idComune){
        return aggiungiRichiestaGenerica(segnalazione, idComune);
    }

    /**
     * aggiunge una richiesta di eliminazione di un contenuto
     * @param richiestaEliminaContenuto richiesta da aggiungere
     * @return true se la richiesta è stata aggiunta, false altrimenti.
     */
    public boolean aggiuntaRichiestaEliminazione(RichiestaEliminaContenuto richiestaEliminaContenuto, String idComune){
        return aggiungiRichiestaGenerica(richiestaEliminaContenuto, idComune);
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
        return aggiungiRichiestaGenerica(richiestaCommand, idComune);
    }

    /**
     * restituisce una richiesta
     * @param richiestaCommand richiesta da restituire
     * @return la richiesta in formato JSON
     */
    public JSONObject getRichiesta(RichiestaCommand richiestaCommand){
        return gestoreComuni.getGestoriComunali().stream()
                .map(GestoreComunale::getGestoreRichieste)
                .map(x->x.getDettagliRichiesta(richiestaCommand))
                .findFirst()
                .orElse(null);
    }


    private boolean aggiungiRichiestaGenerica(RichiestaCommand richiestaCommand, String idComune) {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .getGestoreRichieste().aggiungiRichiesta(richiestaCommand);
    }
}
