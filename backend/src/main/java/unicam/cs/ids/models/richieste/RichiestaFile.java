package unicam.cs.ids.models.richieste;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.view.View;

import java.util.List;

/**
 * Questa classe rappresenta una richiesta riguardante un file.
 */
@Entity
@DiscriminatorValue("RichiestaFile")
public class RichiestaFile extends RichiestaAstratta {

    @JsonView({View.Dettagli.class})
    @OneToOne
    private Contenuto contenuto;

    @JsonView(View.Dettagli.class)
    @Transient
    private List<MultipartFile> files;

    public RichiestaFile(String id, List<MultipartFile> files, Contenuto contenuto) {
        super();
        this.files = files;
        this.contenuto = contenuto;
    }

    public RichiestaFile() {

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
