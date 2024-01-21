package unicam.cs.ids.punti;

import org.json.JSONException;
import unicam.cs.ids.stato.SelettoreStato;
import unicam.cs.ids.stato.Stato;
import unicam.cs.ids.tempo.ObserverTempo;
import unicam.cs.ids.tempo.SempreAttivo;
import unicam.cs.ids.tempo.Tempo;
import org.json.JSONObject;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe per rappresentare un generale contenuto di un punto fisico.
 */
public abstract class Contenuto implements ObserverTempo {

    private final String titolo;
    private final String testo;
    private final List<File> fileMultimediali;
    private final int id;
    private Tempo tempo;
    private Stato stato;

    public Contenuto(String titolo, String testo, List<File> fileMultimediali, int id) {
        this.fileMultimediali = fileMultimediali;
        this.testo = testo;
        this.titolo = titolo;
        this.id = id;
        tempo = new SempreAttivo();
        stato = Stato.APERTO;
    }

    public Contenuto(String titolo, String testo, List<File> fileMultimediali, int id, Tempo tempo) {
        this.fileMultimediali = fileMultimediali;
        this.testo = testo;
        this.titolo = titolo;
        this.id = id;
        this.tempo = tempo;
        stato = SelettoreStato.nuovoStato(Stato.CHIUSO, tempo, LocalDateTime.now());
    }

    /**
     * cambia lo stato del contenuto.
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

    public JSONObject dettagli() throws JSONException {
        return new JSONObject()
                .put("titolo", titolo)
                .put("testo", testo)
                .put("fileMultimediali", fileMultimediali)
                .put("id", id)
                .put("tempo", tempo)
                .put("stato", stato);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contenuto contenuto)) return false;
        return  id == contenuto.id;
    }

    @Override
    public int hashCode() {
        return id * 31 << 4;
    }

    @Override
    public String toString() {
        return "Contenuto{" +
                "id=" + id +
                "titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                ", fileMultimediali=" + fileMultimediali.size() +
                '}';
    }

}
