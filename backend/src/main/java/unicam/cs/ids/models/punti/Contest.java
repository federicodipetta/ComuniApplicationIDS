package unicam.cs.ids.models.punti;

import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.models.stato.SelettoreStato;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.ObserverTempo;
import unicam.cs.ids.models.tempo.Tempo;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe per rappresentare un contest.
 */
public class Contest implements ObserverTempo {

    private final String id;
    private final Utente animatore;
    private final Map<Iscrizione, Integer> iscrizioni;
    private final String titolo;
    private final String descrizione;
    private Tempo tempo;
    private Stato stato;
    private final PuntoFisico puntoFisico;

    public Contest (Utente animatore, String titolo, String descrizione, Tempo tempo, PuntoFisico puntoFisico, String id) {
        this.animatore = animatore;
        this.iscrizioni = new HashMap<>();
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
            iscrizioni.put(new Iscrizione(utente, this, file), 0);
            return true;
        }
        return false;
    }

    /**
     * Questo metodo permette di aggiungere un voto a un'iscrizione del contest.
     * @param iscrizione l'iscrizione a cui aggiungere il voto.
     * @return true se il voto è stato aggiunto, false altrimenti.
     */
    public boolean votazione (Iscrizione iscrizione) {
        if (stato == Stato.APERTO) {
            if (iscrizioni.containsKey(iscrizione)) {
                iscrizioni.put(iscrizione, iscrizioni.get(iscrizione) + 1);
                return true;
            }
        }
        return false;
    }

    /**
     * Aggiorna lo stato del contest.
     * @param dataOra la data e l'ora attuali.
     */
    @Override
    public void update(LocalDateTime dataOra) {
        stato = SelettoreStato.nuovoStato(stato, tempo, dataOra);
    }

    public JSONObject dettagli() {
        try {
            return new JSONObject()
                    .put("id", id)
                    .put("titolo", titolo)
                    .put("descrizione", descrizione)
                    .put("stato", stato)
                    .put("puntoFisico", puntoFisico.toString());
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

    public JSONObject dettagliMinimi() {
        try {
            return new JSONObject()
                    .put("id", id)
                    .put("titolo", titolo)
                    .put("stato", stato);
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }


    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                "animatore=" + animatore +
                ", iscrizioni=" + iscrizioni.size() +
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
        return id.equals(contest.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Stato getStato () {
        return stato;
    }

    public void setStato (Stato stato) {
        this.stato = stato;
    }

    public Map<Iscrizione, Integer> getIscrizioni() {
        return this.iscrizioni;
    }

    public Utente getAnimatore() {
        return this.animatore;
    }

    public String getId () {
        return id;
    }
}
