package unicam.cs.ids.models.richieste;

import org.json.JSONObject;
import unicam.cs.ids.models.punti.Contenuto;

import java.io.File;
import java.util.List;

/**
 * Questa classe rappresenta una richiesta riguardante un file.
 */
public class RichiestaFile extends RichiestaAstratta {

    private final List<File> files;
    private final Contenuto contenuto;

    public RichiestaFile(String id, List<File> files, Contenuto contenuto) {
        super(id);
        this.files = files;
        this.contenuto = contenuto;
    }

    @Override
    public void esegui(boolean accetta) {
        if(accetta) {
            contenuto.aggiungiFile(files);
        }
    }

    @Override
    public JSONObject dettagliMinimi() {
        try {
            return new JSONObject().put("tipo", "caricamento file")
                    .put("file", files.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public JSONObject dettagli() {
        try {
            return new JSONObject().put("tipo", "caricamento file")
                    .put("contenuto", contenuto.dettagli())
                    .put("files", files.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "Richiesta File: " + contenuto.toString() + " - " + files.size();
    }

}
