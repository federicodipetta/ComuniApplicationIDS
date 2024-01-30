package unicam.cs.ids.punti;

import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.ruoli.Utente;
import unicam.cs.ids.stato.SelettoreStato;
import unicam.cs.ids.stato.Stato;
import unicam.cs.ids.tempo.ObserverTempo;
import unicam.cs.ids.tempo.Tempo;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe per rappresentare un contest.
 */
public class Contest implements ObserverTempo {

    private final Utente animatore;
    private final Map<Utente, File> partecipazioni;
    private final String titolo;
    private final String descrizione;
    private Tempo tempo;
    private Stato stato;
    private final PuntoFisico puntoFisico;
    private final String id;

    public Contest (Utente animatore, String titolo, String descrizione, Tempo tempo, PuntoFisico puntoFisico, int id) {
        this.animatore = animatore;
        this.partecipazioni = new HashMap<>();
        this.titolo = titolo;
        this.tempo = tempo;
        this.descrizione = descrizione;
        this.puntoFisico = puntoFisico;
        this.stato = SelettoreStato.nuovoStato(Stato.CHIUSO, tempo, LocalDateTime.now());
        this.id = id;
    }

    /**
     * Questo metodo permette di vedere se un utente è abilitato come creatore del contest.
     * @param utente l'utente da controllare.
     * @return true se l'utente è abilitato, false altrimenti.
     */
    public boolean utenteAbilitato(Utente utente) {
        return animatore.equals(utente);
    }

    /**
     * Questo metodo permette di aggiungere un'iscrizione al contest.
     * @param utente l'utente che vuole iscriversi al contest.
     * @param file il file che l'utente vuole iscrivere al contest.
     * @return true se l'iscrizione è stata aggiunta, false altrimenti.
     */
    public boolean aggiungiIscrizione(Utente utente, File file) {
        if (stato == Stato.APERTO) {
            partecipazioni.put(utente, file);
            return true;
        }
        return false;
    }

    public String getId () {
        return id;
    }

    /**
     * Aggiorna lo stato del contest.
     * @param dataOra la data e l'ora attuali.
     */
    @Override
    public void update(LocalDateTime dataOra) {
        stato = SelettoreStato.nuovoStato(stato, tempo, dataOra);
    }

    public JSONObject dettagli() throws JSONException {
        return new JSONObject()
                .put("id", id)
                .put("animatore", animatore.toString())
                .put("partecipazioni", partecipazioni.size())
                .put("titolo", titolo)
                .put("descrizione", descrizione)
                .put("tempo", tempo.toString())
                .put("stato", stato.toString())
                .put("puntoFisico", puntoFisico.toString());
    }


    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                "animatore=" + animatore +
                ", partecipazioni=" + partecipazioni.size() +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", tempo=" + tempo.toString() +
                ", stato=" + stato.toString() +
                ", " + puntoFisico.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contest contest)) return false;
        return id == contest.id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
