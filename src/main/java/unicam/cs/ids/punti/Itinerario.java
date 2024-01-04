package unicam.cs.ids.punti;

import java.io.File;
import java.util.List;

/**
 * Questa classe rappresenta un itinerario.
 */
public class Itinerario extends Contenuto{

    private final List<Contenuto> contenuti;

    public Itinerario(String titolo, String testo, List<File> fileMultimediali, List<Contenuto> contenuti, int id) {
        super(titolo, testo, fileMultimediali, id);
        this.contenuti = contenuti;
    }

    /**
     * Restituisce la lista dei contenuti dell'itinerario.
     * @return la lista dei contenuti dell'itinerario.
     */
    public List<Contenuto> getContenuti() {
        return contenuti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Itinerario itinerario)) return false;
        return super.equals(itinerario) && contenuti.equals(itinerario.getContenuti());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + contenuti.hashCode();
    }

    @Override
    public String toString() {
        return "Itinerario{" +
                super.toString() + '\'' +
                "contenuti=" + contenuti.size() +
                '}';
    }
}
