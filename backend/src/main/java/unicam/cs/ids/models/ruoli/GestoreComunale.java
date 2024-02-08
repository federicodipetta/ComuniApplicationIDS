package unicam.cs.ids.models.ruoli;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.punti.*;
import unicam.cs.ids.models.servizi.ServizioOSM;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.repositorys.*;

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

    private ContenutiRepository contenutiRepository;

    private ComuniRepository comuniRepository;

    private ContestRepository contestRepository;

    private PuntiFisiciRepository puntiFisiciRepository;

    public GestoreComunale(ComuniRepository comuniRepository, Comune comune, ContestRepository contestRepository,
                           PuntiFisiciRepository puntiFisiciRepository, ContenutiRepository contenutiRepository
                            ,RichiesteRepository richiesteRepository){
        this.contestRepository = contestRepository;
        this.contenutiRepository = contenutiRepository;
        this.puntiFisiciRepository = puntiFisiciRepository;
        this.comuniRepository = comuniRepository;
        this.comune = comune;
        this.contest = new HashSet<>();
        this.puntiFisici = new HashSet<>();
        this.analizzatorePuntoFisico = new ProxyAnalizzatorePuntoFisico(new AnalizzatorePuntoFisico(new ServizioOSM()));
        this.gestoreRichieste = new GestoreRichieste(richiesteRepository,comune.id());
    }

    /**
     * Questo metodo permette di valutare se un punto fisico è contenuto in un comune.
     * @param puntoFisico il punto fisico da valutare.
     * @return true se il punto fisico è contenuto nel comune, false altrimenti.
     */
    public boolean valutaPuntoFisico(PuntoFisico puntoFisico) {
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
        if(this.contestRepository.findById(contest.getId()).isEmpty()) {
            this.contestRepository.save(contest);
            return true;
        }
        return false;
    }

    /**
     * Questo metodo permette di ricevere le iscrizioni vincenti di un contest.
     * @param contest il contest di cui si vogliono le iscrizioni vincenti.
     * @return le iscrizioni vincenti del contest.
     */
    public List<Iscrizione> getIscrizioniVincenti(Contest contest) {

        // TODO: implementare
        return null;

   // int max = contest.getIscrizioni().stream()
   //            .mapToInt(Iscrizione::getPunti)
   //            .max()
   //             .orElse(0);
    //     return contest.getIscrizioni().stream()
    //            .filter(iscrizione -> iscrizione.getPunti() == max)
    //            .collect(Collectors.toList());

    }

    /**
     * Questo metodo chiude un contest se è aperto.
     * @param contest il contest da chiudere.
     * @return true se il contest è stato chiuso, false altrimenti.
     */
    public boolean chiudiContest(Contest contest) {
        if (contest.getStato() == Stato.APERTO) {
            this.contestRepository.findById(contest.getId()).ifPresent(contest1 -> {
                contest1.setStato(Stato.CHIUSO);
                this.contestRepository.save(contest1);
            });
            return true;
        }
        return false;
    }

    /**
     * Questo metodo ritorna i dettagli dei contenuti dentro un punto fisico.
     * @param puntoFisico il punto fisico
     * @return i dettagli dei contenuti del punto fisico
     */
    public Set<Contenuto> getDettagliContenuti(PuntoFisico puntoFisico) {
        PuntoFisico punto = this.puntiFisiciRepository.getReferenceById(puntoFisico.getCoordinate());
        if(punto != null) {
            return punto.getContenuti();
        } else return new HashSet<>();
    }

    /**
     * Questo metodo permette di aggiungere un contenuto a un punto fisico.
     * @param contenuto il contenuto da aggiungere
     * @param puntoFisico il punto fisico a cui aggiungere il contenuto
     */
    public boolean aggiungiContenuto(Contenuto contenuto, PuntoFisico puntoFisico) {
        Contenuto con = this.contenutiRepository.save(contenuto);
        PuntoFisico pf ;
        puntoFisico.setIdc(this.comune.id());
        if(!this.puntiFisiciRepository.existsById(puntoFisico.getCoordinate())) {//Todo: elimina commento finiti i test
           // if(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico, comune)) {
            pf = this.puntiFisiciRepository.save(puntoFisico);
        //    }
        } else pf = this.puntiFisiciRepository.getReferenceById(puntoFisico.getCoordinate());


        pf.getContenuti().add(con);
        pf = this.puntiFisiciRepository.save(pf);
        return true;
    }

    /**
     * Questo metodo permette di eliminare un contenuto da un punto fisico.
     * @param contenuto il contenuto da eliminare.
     * @param puntoFisico il punto fisico da cui eliminare il contenuto.
     * @return true se il contenuto è stato eliminato, false altrimenti.
     */
    public boolean eliminaContenuto(Contenuto contenuto, PuntoFisico puntoFisico) {
        return eliminaContenuto(contenuto);
    }

    /**
     * Questo metodo elimina un contenuto cercandolo in tutti i punti fisici.
     * @param contenuto il contenuto da eliminare.
     * @return true se il contenuto è stato eliminato, false altrimenti.
     */
    public boolean eliminaContenuto(Contenuto contenuto) {
        Contenuto con = this.contenutiRepository.getReferenceById(contenuto.getId());
        con.setStato(Stato.ELIMINATO);
        this.contenutiRepository.save(con);
        return true;
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
        return this.contestRepository.getReferenceById(id);
    }

    /**
     * Questo metodo ritorna un contenuto a partire dal suo id.
     * @param id l'id del contenuto.
     * @return il contenuto.
     */
    public Contenuto getContenutoById(String id) {
        return this.contenutiRepository.getReferenceById(id);
    }

    /**
     * Questo metodo ritorna il gestore delle richieste.
     * @return il gestore delle richieste.
     */
    public GestoreRichieste getGestoreRichieste() {
        return gestoreRichieste;
    }

    public Set<PuntoFisico> getPuntiFisici() {
        return new HashSet<>(this.puntiFisiciRepository.findAll().stream().filter(puntoFisico -> this.comune.id().equals(puntoFisico.getIdc())).toList());
    }
}
