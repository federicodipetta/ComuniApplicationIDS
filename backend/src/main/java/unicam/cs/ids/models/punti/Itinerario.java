package unicam.cs.ids.models.punti;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.tempo.Tempo;

import java.io.File;
import java.util.List;

/**
 * Questa classe rappresenta un itinerario.
 */
public class Itinerario extends Contenuto{

    private final List<Contenuto> contenuti;

    public Itinerario(String titolo, String testo, List<MultipartFile> fileMultimediali, List<Contenuto> contenuti, String id) {
        super(id, titolo, testo, fileMultimediali);
        this.contenuti = contenuti;
    }
    public Itinerario(String titolo, String testo, List<MultipartFile> fileMultimediali, List<Contenuto> contenuti, String id, Tempo tempo) {
        super(id,titolo, testo, fileMultimediali, tempo);
        this.contenuti = contenuti;
    }

    @Override
    public JSONObject dettagli() {
        try {
            JSONObject dettagli = super.dettagli();
            dettagli.put("contenuti", contenuti.size());
            return dettagli;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "Itinerario{" +
                super.toString() + '\'' +
                "contenuti=" + contenuti.size() +
                '}';
    }
}
