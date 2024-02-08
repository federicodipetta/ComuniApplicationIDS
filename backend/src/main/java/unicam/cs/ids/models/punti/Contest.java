package unicam.cs.ids.models.punti;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.models.stato.SelettoreStato;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.ObserverTempo;
import unicam.cs.ids.models.tempo.TempoAstratto;
import unicam.cs.ids.view.View;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe per rappresentare un contest.
 */
@Entity
public class Contest implements ObserverTempo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private  String id;
    @ManyToOne
    @Transient
    private  Utente animatore;
    @Transient
    private  Map<Iscrizione, Integer> iscrizioni;
    @JsonView(View.Dettagli.class)
    private  String titolo;

    private  String descrizione;
    @OneToOne(cascade = CascadeType.ALL)
    private TempoAstratto tempo;

    private Stato stato;
    @ManyToOne
    private  PuntoFisico puntoFisico;


    public Contest (Utente animatore, String titolo, String descrizione, TempoAstratto tempo, PuntoFisico puntoFisico) {
        this.animatore = animatore;
        this.iscrizioni = new HashMap<>();
        this.titolo = titolo;
        this.tempo = tempo;
        this.descrizione = descrizione;
        this.puntoFisico = puntoFisico;
        this.stato = SelettoreStato.nuovoStato(Stato.CHIUSO, tempo, LocalDateTime.now());
    }

    public Contest() {

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
    public boolean aggiungiIscrizione(Utente utente, MultipartFile file) {
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