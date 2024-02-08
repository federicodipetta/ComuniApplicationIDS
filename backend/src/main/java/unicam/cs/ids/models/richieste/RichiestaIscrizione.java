package unicam.cs.ids.models.richieste;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.punti.Contest;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.view.View;

/**
 * Questa classe rappresenta una richiesta riguardante un'iscrizione.
 */
@Entity
@DiscriminatorValue("RichiestaIscrizione")
public class RichiestaIscrizione extends RichiestaAstratta {

    @JsonView({View.Dettagli.class})
    @Transient
    private MultipartFile file;
    @JsonView({View.Dettagli.class})
    @OneToOne
    private Utente utente;
    @OneToOne
    private Contest contest;

    public RichiestaIscrizione(MultipartFile file, Contest contest, Utente utente) {
        super();
        this.file = file;
        this.contest = contest;
        this.utente = utente;
    }

    public RichiestaIscrizione() {

    }

    @Override
    public void esegui(boolean accetta) {
        if (accetta) {
            contest.aggiungiIscrizione(utente, file);
        }
    }

    @Override
    public JSONObject dettagliMinimi() {
        try {
            return new JSONObject().put("tipo", "iscrizione contest")
                    .put("file", file.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject dettagli() {
        try {
            return new JSONObject().put("tipo", "iscrizione contest")
                    .put("file", file.toString())
                    .put("utente", utente.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "RichiestaIscrizione{" +
                "file=" + file.toString() +
                ", " + utente.toString() +
                ", " + contest.toString() +
                '}';
    }

    public Utente getAnimatore() {
        return contest.getAnimatore();
    }
}
