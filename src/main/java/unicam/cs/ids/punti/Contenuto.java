package unicam.cs.ids.punti;

import java.io.File;
import java.util.List;

/**
 * Classe per rappresentare un generale contenuto di un punto fisico.
 */
public abstract class Contenuto {

    private final String titolo;
    private final String testo;
    private final List<File> fileMultimediali;
    private final int id;

    public Contenuto(String titolo, String testo, List<File> fileMultimediali, int id) {
        this.fileMultimediali = fileMultimediali;
        this.testo = testo;
        this.titolo = titolo;
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contenuto contenuto)) return false;
        return titolo.equals(contenuto.titolo) &&
                testo.equals(contenuto.testo) &&
                fileMultimediali.equals(contenuto.fileMultimediali) &&
                id == contenuto.id;
    }

    @Override
    public int hashCode() {
        return titolo.hashCode() + fileMultimediali.hashCode() + testo.hashCode();
    }

    @Override
    public String toString() {
        return "Contenuto{" +
                "titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                ", fileMultimediali=" + fileMultimediali.size() +
                '}';
    }

    /**
     * Metodo per aggiungere un file multimediale al contenuto.
     * @param file Il file da aggiungere.
     * @return true se il file è stato aggiunto, false altrimenti.
     */
    public boolean aggiungiFile(List<File> file) {
    	return fileMultimediali.addAll(file);
    }

    /**
     * Metodo per rimuovere un file multimediale dal contenuto.
     * @param file Il file da rimuovere.
     * @return true se il file è stato rimosso, false altrimenti.
     */
    public boolean rimuoviFile(List<File> file) {
    	return fileMultimediali.removeAll(file);
    }

}
