package unicam.cs.ids.ruoli;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.Comune;
import unicam.cs.ids.punti.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Questa classe rappresenta un gestore comunale.
 */
public class GestoreComunale {

    private final Comune comune;
    private final Set<Contest> contest;
    private final Set<PuntoFisico> puntiFisici;
    private final AnalizzatorePuntoFisico analizzatorePuntoFisico;
    private final GestoreRichieste gestoreRichieste;

    public GestoreComunale(Comune comune) {
        this.comune = comune;
        this.contest = new HashSet<>();
        this.puntiFisici = new HashSet<>();
        this.analizzatorePuntoFisico = new AnalizzatorePuntoFisico();
        this.gestoreRichieste = new GestoreRichieste();
    }

    /**
     * Questo metodo permette di aggiungere un contenuto a un punto fisico.
     * @param contenuto il contenuto da aggiungere
     * @param puntoFisico il punto fisico a cui aggiungere il contenuto
     */
    public boolean aggiungiContenuto(Contenuto contenuto, PuntoFisico puntoFisico) {
        return puntoFisico.aggiungiContenuto(contenuto);
    }

    /**
     * Questo metodo permette di aggiungere un punto fisico a un gestore comunale.
     * @param puntoFisico il punto fisico da aggiungere
     */
    public boolean aggiungiPuntoFisico(PuntoFisico puntoFisico) throws JSONException, IOException {
        if (this.analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico, comune)) {
            return this.puntiFisici.add(puntoFisico);
        }
        return false;
    }

    /**
     * Questo metodo permette di aggiungere un contest a un gestore comunale.
     * @param contest il contest da aggiungere
     */
    public boolean aggiungiContest(Contest contest) {
        return this.contest.add(contest);
    }

    /**
     * Questo metodo ritorna un contest a partire dal suo id.
     * @param id l'id del contest
     * @return il contest
     */
    public Contest getContest(String id) {
        return contest.stream()
                .filter(contest -> contest.getId().equals(id)).findFirst()
                .orElse(null);
    }


    /**
     * Questo metodo ritorna il comune del gestore comunale
     * @return il comune del gestore comunale
     */
    public Comune getComune() {
        return comune;
    }

    /**
     * Questo metodo ritorna o dettagli di un puntofisico, nelle quali potremmo trovare
     * le coordinate del punto, il numero dei contenuti.
     * @return
     * @throws JSONException
     */
    public JSONArray getDettagliPuntiFisici() throws JSONException {
        //TODO: implementare
        return null;
    }

    /**
     * Questo metodo ritorna i dettagli dei contenuti dentro un punto fisico.
     * @param puntoFisico il punto fisico
     * @return i dettagli dei contenuti del punto fisico
     * @throws JSONException
     */
    public JSONArray getDettagliContenuti(PuntoFisico puntoFisico) throws JSONException {
        return puntiFisici.stream().filter(x -> x.equals(puntoFisico))
                .findFirst().orElseThrow().getContenuti().stream()
                .map(Contenuto::dettagli).collect(JSONArray::new, JSONArray::put, JSONArray::put);
    }


    @Override
    public String toString() {
        return "GestoreComunale{" +
                "Comune=" + comune +
                ", Contest=" + contest.size() +
                ", PuntiFisici=" + puntiFisici.size() +
                '}';
    }

    public boolean eliminaContenuto(Contenuto contenuto, PuntoFisico puntoFisico) {
        //TODO: implementare
        return false;
    }

    public Contest getContestById(String idContest) {
        //TODO: implementare
        return null;
    }

    public List<Iscrizioni> getIscrizioniVincenti(Contest contestById) {
        //TODO: implementare
        return null;
    }
}
