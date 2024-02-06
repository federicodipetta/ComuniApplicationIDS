package unicam.cs.ids.models.richieste;

import com.fasterxml.jackson.annotation.JsonView;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.view.View;

import java.io.File;
import java.util.List;

/**
 * Questa classe rappresenta una richiesta riguardante un file.
 */
public class RichiestaFile extends RichiestaAstratta {

    @JsonView({View.Dettagli.class})
    private final Contenuto contenuto;
    @JsonView(View.Dettagli.class)
    private final List<MultipartFile> files;

    public RichiestaFile(String id, List<MultipartFile> files, Contenuto contenuto) {
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
