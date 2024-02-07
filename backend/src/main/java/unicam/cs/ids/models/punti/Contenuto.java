package unicam.cs.ids.models.punti;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.stato.SelettoreStato;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.ObserverTempo;
import unicam.cs.ids.models.tempo.SempreAttivo;
import org.json.JSONObject;
import unicam.cs.ids.models.tempo.TempoAstratto;
import unicam.cs.ids.view.View;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
/**
 * Classe per rappresentare un generale contenuto di un punto fisico.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Contenuto implements ObserverTempo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonView(View.DettagliMinimi.class)
    private  String id;
    @JsonView(View.DettagliMinimi.class)
    private  String titolo;
    @JsonView(View.Dettagli.class)
    private  String testo;
    @JsonView(View.Dettagli.class)
    @Transient
    private  List<MultipartFile> fileMultimediali;
    @OneToOne(cascade = CascadeType.ALL)
    private TempoAstratto tempo;
    @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
    private Stato stato;



    public Contenuto(String titolo, String testo, List<MultipartFile> fileMultimediali) {
        this.titolo = titolo;
        this.testo = testo;
        this.fileMultimediali = fileMultimediali;
        tempo = new SempreAttivo();
        stato = Stato.APERTO;
    }

    public Contenuto(String titolo, String testo, List<MultipartFile> fileMultimediali, TempoAstratto tempo) {
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.fileMultimediali = fileMultimediali;
        this.tempo = tempo;
        stato = SelettoreStato.nuovoStato(Stato.CHIUSO, tempo, LocalDateTime.now());
    }

    public Contenuto(String titolo, String testo, List<MultipartFile> fileMultimediali, TempoAstratto tempo, Stato stato) {
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.fileMultimediali = fileMultimediali;
        this.tempo = tempo;
        this.stato = stato;
    }

    public Contenuto() {

    }

    /**
     * Aggiorna lo stato del contenuto.
     * @param dataOra la data e l'ora attuali.
     */
    @Override
    public void update(LocalDateTime dataOra) {
        stato = SelettoreStato.nuovoStato(stato, tempo, dataOra);
    }

    /**
     * Metodo per aggiungere una lista di file multimediali al contenuto.
     * @param file i file da aggiungere.
     * @return true se i file sono stato aggiunto, false altrimenti.
     */
    public boolean aggiungiFile(List<MultipartFile> file) {
        return fileMultimediali.addAll(file);
    }

    /**
     * Metodo per rimuovere dei file multimediali dal contenuto.
     * @param file i file da rimuovere.
     * @return true se almeno un file è stato rimosso, false altrimenti.
     */
    public boolean rimuoviFile(List<File> file) {
        return fileMultimediali.removeAll(file);
    }

    public JSONObject dettagli()  {
        try {
            return new JSONObject()
                    .put("id", id)
                    .put("titolo", titolo)
                    .put("testo", testo)
                    .put("fileMultimediali", fileMultimediali)
                    .put("stato", stato);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject dettagliMinimi() {
        try {
            return new JSONObject()
                    .put("id", id)
                    .put("titolo", titolo)
                    .put("stato", stato);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contenuto contenuto)) return false;
        return  id.equals(contenuto.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                ", fileMultimediali=" + fileMultimediali.size()+",";
    }

    /**
     * Ritorna l'id del contenuto.
     * @return l'id del contenuto.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Cambia lo stato del contenuto.
     * @param stato il nuovo stato.
     */
    public void setStato(Stato stato) {
        this.stato = stato;
    }

    /**
     * @return lo stato del contenuto.
     */
    public Stato getStato() {
        return this.stato;
    }

    public Tempo getTempo() {
        return tempo;
    }

}
