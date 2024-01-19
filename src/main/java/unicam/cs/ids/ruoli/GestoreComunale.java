package unicam.cs.ids.ruoli;

import org.json.JSONException;
import unicam.cs.ids.Comune;
import unicam.cs.ids.punti.AnalizzatorePuntoFisico;
import unicam.cs.ids.punti.Contenuto;
import unicam.cs.ids.punti.Contest;
import unicam.cs.ids.punti.PuntoFisico;

import java.io.IOException;
import java.util.HashSet;
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
    public Contest getContest(int id) {
        for (Contest c : contest) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public Comune getComune() {
        return comune;
    }

    @Override
    public String toString() {
        return "GestoreComunale{" +
                "Comune=" + comune +
                ", Contest=" + contest.size() +
                ", PuntiFisici=" + puntiFisici.size() +
                '}';
    }

}
