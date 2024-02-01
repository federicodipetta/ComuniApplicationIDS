package unicam.cs.ids.ruoli;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.Comune;
import unicam.cs.ids.punti.*;
import unicam.cs.ids.servizi.ServizioOSM;
import unicam.cs.ids.stato.Stato;

import java.io.IOException;
import java.util.*;

/**
 * Questa classe rappresenta un gestore comunale.
 */
public class GestoreComunale {

    private final Comune comune;
    private final Set<Contest> contest;
    private final Set<PuntoFisico> puntiFisici;
    private final IAnalizzatorePuntoFisico analizzatorePuntoFisico;
    private final GestoreRichieste gestoreRichieste;

    public GestoreComunale(Comune comune) {
        this.comune = comune;
        this.contest = new HashSet<>();
        this.puntiFisici = new HashSet<>();
        this.analizzatorePuntoFisico = new ProxyAnalizzatorePuntoFisico(new AnalizzatorePuntoFisico(new ServizioOSM()));
        this.gestoreRichieste = new GestoreRichieste();
    }

    /**
     * Questo metodo permette di valutare se un punto fisico è contenuto in un comune.
     * @param puntoFisico il punto fisico da valutare.
     * @return true se il punto fisico è contenuto nel comune, false altrimenti.
     */
    public boolean valutaPuntoFisico(PuntoFisico puntoFisico) throws JSONException, IOException {
        return this.analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico, comune);
    }

    /**
     * Questo metodo permette di avere i dettagli di tutti i contest di un certo animatore.
     * @param utente l'animatore di cui si vogliono i contest.
     * @return i dettagli dei contest dell'animatore.
     */
    public JSONObject getDettagliContest(Utente utente) {
        try {
            return this.contest.stream()
                    .filter(contest -> contest.getAnimatore().equals(utente))
                    .map(Contest::dettagli)
                    .collect(JSONArray::new, JSONArray::put, JSONArray::put)
                    .getJSONObject(0);
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Questo metodo permette di aggiungere un contest a un gestore comunale.
     * @param contest il contest da aggiungere
     */
    public boolean aggiungiContest(Contest contest) {
        return this.contest.add(contest);
    }

    /**
     * Questo metodo permette di ricevere le iscrizioni vincenti di un contest.
     * @param contest il contest di cui si vogliono le iscrizioni vincenti.
     * @return le iscrizioni vincenti del contest.
     */
    public List<Iscrizione> getIscrizioniVincenti(Contest contest) {
        return contest.getIscrizioni().entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), contest.getIscrizioni().values().stream().max(Integer::compareTo).get()))
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Questo metodo chiude un contest se è aperto.
     * @param contest il contest da chiudere.
     * @return true se il contest è stato chiuso, false altrimenti.
     */
    public boolean chiudiContest(Contest contest) {
        if (contest.getStato() == Stato.APERTO) {
            contest.setStato(Stato.CHIUSO);
            return true;
        }
        return false;
    }

    /**
     * Questo metodo ritorna i dettagli dei contenuti dentro un punto fisico.
     * @param puntoFisico il punto fisico
     * @return i dettagli dei contenuti del punto fisico
     */
    public JSONArray getDettagliContenuti(PuntoFisico puntoFisico) {
        return puntoFisico.getContenuti().stream()
                .map(Contenuto::dettagli)
                .collect(JSONArray::new, JSONArray::put, JSONArray::put);
    }

    /**
     * Questo metodo permette di aggiungere un contenuto a un punto fisico.
     * @param contenuto il contenuto da aggiungere
     * @param puntoFisico il punto fisico a cui aggiungere il contenuto
     */
    public boolean aggiungiContenuto(Contenuto contenuto, PuntoFisico puntoFisico) {
        return puntoFisico.getContenuti().add(contenuto);
    }

    /**
     * Questo metodo permette di eliminare un contenuto da un punto fisico.
     * @param contenuto il contenuto da eliminare.
     * @param puntoFisico il punto fisico da cui eliminare il contenuto.
     * @return true se il contenuto è stato eliminato, false altrimenti.
     */
    public boolean eliminaContenuto(Contenuto contenuto, PuntoFisico puntoFisico) {
        if (puntoFisico.getContenuti().contains(contenuto)) {
            puntoFisico.getContenuti().remove(contenuto);
            if (puntoFisico.getContenuti().isEmpty()) {
                return puntiFisici.remove(puntoFisico);
            }
        }
        return false;
    }

    /**
     * Questo metodo elimina un contenuto cercandolo in tutti i punti fisici.
     * @param contenuto il contenuto da eliminare.
     * @return true se il contenuto è stato eliminato, false altrimenti.
     */
    public boolean eliminaContenuto(Contenuto contenuto) {
        for (PuntoFisico puntoFisico : puntiFisici) {
            if (puntoFisico.getContenuti().contains(contenuto)) {
                puntoFisico.getContenuti().remove(contenuto);
                if (puntoFisico.getContenuti().isEmpty()) {
                    return puntiFisici.remove(puntoFisico);
                }
            }
        }
        return false;
    }



    /**
     * Questo metodo ritorna i dettagli di un punto fisico, nelle quali potremmo trovare
     * le coordinate del punto, il numero dei contenuti.
     * @return
     */
    public JSONArray getDettagliPuntiFisici() {
        // TODO: implementare
        return null;
    }




    @Override
    public String toString() {
        return "GestoreComunale{" +
                "Comune=" + comune +
                ", Contest=" + contest.size() +
                ", PuntiFisici=" + puntiFisici.size() +
                '}';
    }

    /**
     * Questo metodo ritorna il comune del gestore comunale
     * @return il comune del gestore comunale
     */
    public Comune getComune() {
        return comune;
    }

    /**
     * Questo metodo ritorna un contest a partire dal suo id.
     * @param id l'id del contest.
     * @return il contest.
     */
    public Contest getContestById(String id) {
        return contest.stream()
                .filter(contest -> contest.getId().equals(id)).findFirst()
                .orElse(null);
    }

    /**
     * Questo metodo ritorna un contenuto a partire dal suo id.
     * @param id l'id del contenuto.
     * @return il contenuto.
     */
    public Contenuto getContenutoById(String id) {
        return puntiFisici.stream()
                .flatMap(puntoFisico -> puntoFisico.getContenuti().stream())
                .filter(contenuto -> contenuto.getId().equals(id)).findFirst()
                .orElse(null);
    }

    /**
     * Questo metodo ritorna il gestore delle richieste.
     * @return il gestore delle richieste.
     */
    public GestoreRichieste getGestoreRichieste() {
        return gestoreRichieste;
    }
}
