package unicam.cs.ids.models.punti;

import jakarta.persistence.*;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.tempo.TempoAstratto;

import java.util.List;

/**
 * Questa classe rappresenta un itinerario.
 */
@Entity
@DiscriminatorValue("Itinerario")
public class Itinerario extends Contenuto{
    @ManyToMany
    @JoinTable(
            name = "itinerario_contenuti",
            joinColumns = @JoinColumn(name = "itinerario_id"),
            inverseJoinColumns = @JoinColumn(name = "contenuto_id")
    )
    private  List<Contenuto> contenuti;

    public Itinerario(String titolo, String testo, List<MultipartFile> fileMultimediali, List<Contenuto> contenuti) {
        super(titolo, testo, fileMultimediali);
        this.contenuti = contenuti;
    }
    public Itinerario(String titolo, String testo, List<MultipartFile> fileMultimediali, List<Contenuto> contenuti,  TempoAstratto tempo) {
        super(titolo, testo, fileMultimediali, tempo);
        this.contenuti = contenuti;
    }

    public Itinerario() {

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
