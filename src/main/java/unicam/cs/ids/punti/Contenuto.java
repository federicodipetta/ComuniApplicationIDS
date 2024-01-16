package unicam.cs.ids.punti;

import unicam.cs.ids.stato.SelettoreStato;
import unicam.cs.ids.stato.Stato;
import unicam.cs.ids.tempo.ObserverTempo;
import unicam.cs.ids.tempo.SempreAttivo;
import unicam.cs.ids.tempo.Tempo;

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
     * Metodo per ottenere il testo del contenuto.
     * @return il testo del contenuto.
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Metodo per ottenere il titolo del contenuto.
     * @return il titolo del contenuto.
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Metodo per ottenere la lista dei file multimediali del contenuto.
     * @return la lista dei file multimediali del contenuto.
     */
    public List<File> getFileMultimediali() {
        return fileMultimediali;
    }

    /**
     * Metodo per ottenere l'id del contenuto.
     * @return l'id del contenuto.
     */
    public int getId() {
        return id;
    }

    @Override
    public void update(LocalDateTime dataOra) {
        stato = SelettoreStato.nuovoStato(stato, tempo, dataOra);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contenuto contenuto)) return false;
        return  titolo.equals(contenuto.titolo) &&
                testo.equals(contenuto.testo) &&
                fileMultimediali.equals(contenuto.fileMultimediali) &&
                id == contenuto.id;
    }

    @Override
    public int hashCode() {
        return id+titolo.hashCode() + fileMultimediali.hashCode() + testo.hashCode();
    }

    @Override
    public String toString() {
        return "Contenuto{" +
                "titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                ", fileMultimediali=" + fileMultimediali.size() +
                '}';
    }

}
