package unicam.cs.ids.models.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.cs.ids.configurazioni.GestorePiattaformaBuilder;
import unicam.cs.ids.models.richieste.*;
import unicam.cs.ids.models.ruoli.*;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.repositorys.RichiesteRepository;

import java.util.Collection;

/**
 * Questa classe è un controller per le richieste.
 */
@Service
public class ControllerRichieste {

    GestoreComuni gestoreComuni;

    GestoreUtenti gestoreUtenti;

    RichiesteRepository richiesteRepository;


    @Autowired
    public ControllerRichieste(RichiesteRepository richiesteRepository, GestorePiattaformaBuilder gestorePiattaformaBuilder) {
        var gestorePiattaforma = GestorePiattaforma.getInstance(gestorePiattaformaBuilder);
        this.gestoreComuni = gestorePiattaforma.getGestoreComuni();
        this.gestoreUtenti = gestorePiattaforma.getGestoreUtenti();
        this.richiesteRepository = richiesteRepository;
    }

    /**
     * Costruisce un ControllerRichieste.
     * @param gestoreUtenti Il gestore degli utenti.
     * @param gestoreComuni Il gestore dei comuni.
     */
    public ControllerRichieste(GestoreComuni gestoreComuni, GestoreUtenti gestoreUtenti) {
        this.gestoreUtenti = gestoreUtenti;
        this.gestoreComuni = gestoreComuni;
    }


    /**
     * Aggiunge una richiesta di aggiunta di un contenuto.
     * @param richiestaContenuto la richiesta da aggiungere.
     * @param idComune l'id del comune in cui aggiungerla.
     * @return true se la richiesta è stata aggiunta, false altrimenti.
     */
    public boolean aggiungiRichiestaAggiunta(RichiestaContenuto richiestaContenuto, String idComune){
        richiestaContenuto.getContenuto().setStato(Stato.DA_ACCETTARE);
        this.gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .aggiungiContenuto(richiestaContenuto.getContenuto(), richiestaContenuto.getPuntoFisico());

        return aggiungiRichiestaGenerica(richiestaContenuto, idComune);
    }

    /**
     * Aggiunge una segnalazione.
     * @param segnalazione segnalazione da aggiungere.
     * @return true se la segnalazione è stata aggiunta, false altrimenti.
     */
    public boolean aggiungiSegnalazione(Segnalazione segnalazione, String idComune){
        return aggiungiRichiestaGenerica(segnalazione, idComune);
    }

    /**
     * Aggiunge una richiesta di eliminazione di un contenuto.
     * @param richiestaEliminaContenuto richiesta da aggiungere.
     * @return true se la richiesta è stata aggiunta, false altrimenti.
     */
    public boolean aggiuntaRichiestaEliminazione(RichiestaEliminaContenuto richiestaEliminaContenuto, String idComune){
        return aggiungiRichiestaGenerica(richiestaEliminaContenuto, idComune);
    }

    /**
     * La richiesta da aggiungere.
     * @param richiestaCommand la richiesta.
     * @param idComune il comune in cui aggiungerla.
     * @return true se la richiesta è stata aggiunta, false altrimenti.
     * @throws IllegalArgumentException se non è una richiesta normale,
     *         quindi quelle legate al contest ad esempio.
     */
    public boolean aggiungiRichiesta(RichiestaAstratta richiestaCommand, String idComune){
        return aggiungiRichiestaGenerica(richiestaCommand, idComune);
    }

    /**
     * Aggiunge una richiesta di iscrizione a un contest.
     * @param richiestaIscrizione richiesta da aggiungere.
     * @param idComune comune in cui aggiungere la richiesta.
     * @return true se la richiesta è stata aggiunta, false altrimenti.
     */
    public boolean aggiungiRichiestaIscrizione(RichiestaIscrizione richiestaIscrizione, String idComune){
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .getGestoreRichieste().aggiungiRichiestaContest(richiestaIscrizione,richiestaIscrizione.getAnimatore());

    }


    /**
     * Valuta una richiesta
     * @param richiestaAstratta richiesta da valutare
     * @param idComune comune in cui valutare la richiesta
     * @param accettazione true se la richiesta è accettata, false altrimenti.
     * @return true se la richiesta è stata valutata, false altrimenti.
     * @see GestoreRichieste#valutaRichiesta(RichiestaAstratta, boolean) fanno una funzione simile ma questo metodo è più efficente
     */
    public boolean valutaRichiesta(RichiestaAstratta richiestaAstratta, String idComune, boolean accettazione){
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .getGestoreRichieste().valutaRichiesta(richiestaAstratta, accettazione);
    }


    public Collection<RichiestaAstratta> getRichieste(String idComune){
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .getGestoreRichieste().getRichieste();
    }

    public RichiestaAstratta getRichiesta(String id, String idComune){
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .getGestoreRichieste().getRichiestaById(id);
    }


    private boolean aggiungiRichiestaGenerica(RichiestaAstratta richiestaCommand, String idComune) {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .getGestoreRichieste().aggiungiRichiesta(richiestaCommand);
    }



    /**
     * Restituisce le richieste di iscrizione ad un contest.
     * @param utente animatore del contest.
     * @return le richieste di iscrizione a un contest in formato JSON.
     */
    public JSONArray getRichiesteContest(String utente){
        return gestoreComuni.getGestoriComunali().stream()
                .map(GestoreComunale::getGestoreRichieste)
                .map(x->x.getDettagliRichiesteContest(gestoreUtenti.getUtenteById(utente)))
                .collect(JSONArray::new, JSONArray::put, JSONArray::put);
    }

    /**
     * Valuta una richiesta generica.
     * @param richiestaCommand richiesta da valutare.
     * @param accettazione true se la richiesta è accettata, false altrimenti.
     * @return true se la richiesta è stata valutata, false altrimenti.
     */
    public boolean valutaRichiesta(RichiestaAstratta richiestaCommand, boolean accettazione){
        return gestoreComuni.getGestoriComunali().stream()
                .map(GestoreComunale::getGestoreRichieste)
                .anyMatch(x->x.valutaRichiesta(richiestaCommand, accettazione));
    }



    /**
     * Restituisce una richiesta.
     * @param richiestaCommand richiesta da restituire.
     * @return la richiesta in formato JSON.
     */
    public JSONObject getRichiesta(RichiestaCommand richiestaCommand){
        return gestoreComuni.getGestoriComunali().stream()
                .map(GestoreComunale::getGestoreRichieste)
                .map(x->x.getDettagliRichiesta(richiestaCommand))
                .findFirst()
                .orElse(null);
    }

}
