package unicam.cs.ids.models.punti;

import com.fasterxml.jackson.annotation.JsonView;
import org.json.JSONException;
import unicam.cs.ids.models.stato.SelettoreStato;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.ObserverTempo;
import unicam.cs.ids.models.tempo.SempreAttivo;
import unicam.cs.ids.models.tempo.Tempo;
import org.json.JSONObject;
import unicam.cs.ids.view.View;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe per rappresentare un generale contenuto di un punto fisico.
 */
public abstract class Contenuto implements ObserverTempo {

    @JsonView(View.DettagliMinimi.class)
    private final String id;
    @JsonView(View.DettagliMinimi.class)
    private final String titolo;
    @JsonView(View.Dettagli.class)
    private final String testo;
    @JsonView(View.Dettagli.class)
    private final List<File> fileMultimediali;
    @JsonView(View.Dettagli.class)
    private Tempo tempo;
    @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
    private Stato stato;

    public Contenuto(String id, String titolo, String testo, List<File> fileMultimediali) {
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.fileMultimediali = fileMultimediali;
        tempo = new SempreAttivo();
        stato = Stato.APERTO;
    }

    public Contenuto(String id, String titolo, String testo, List<File> fileMultimediali, Tempo tempo) {
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.fileMultimediali = fileMultimediali;
        this.tempo = tempo;
        stato = SelettoreStato.nuovoStato(Stato.CHIUSO, tempo, LocalDateTime.now());
    }

    public Contenuto(String id, String titolo, String testo, List<File> fileMultimediali, Tempo tempo, Stato stato) {
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.fileMultimediali = fileMultimediali;
        this.tempo = tempo;
        this.stato = stato;
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
    public boolean aggiungiFile(List<File> file) {
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

}
