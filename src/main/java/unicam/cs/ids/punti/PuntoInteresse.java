package unicam.cs.ids.punti;

import java.io.File;
import java.util.List;

/**
 * Classe per rappresentare un Punto di Interesse.
 */
public class PuntoInteresse extends Contenuto {

    public PuntoInteresse(String titolo, String testo, List<File> fileMultimediali, String id) {
        super(id,titolo, testo, fileMultimediali);
    }

    @Override
    public String toString() {
        return "PuntoInteresse{" +
                super.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuntoInteresse puntoInteresse)) return false;
        return super.equals(puntoInteresse);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
