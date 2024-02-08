package unicam.cs.ids.models.richieste;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.punti.Contest;
import unicam.cs.ids.models.punti.Iscrizione;
import unicam.cs.ids.models.ruoli.Utente;

import java.io.File;

/**
 * Questa classe rappresenta una richiesta riguardante un'iscrizione.
 */
public class RichiestaIscrizione extends RichiestaAstratta {

    private final MultipartFile file;
    private final Utente utente;
    private final Contest contest;

    public RichiestaIscrizione(String id, MultipartFile file, Contest contest, Utente utente) {
        super(id);
        this.file = file;
        this.contest = contest;
        this.utente = utente;
    }

    @Override
    public void esegui(boolean accetta) {
        if (accetta) {
            contest.aggiungiIscrizione(new Iscrizione(utente, contest, file));
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
