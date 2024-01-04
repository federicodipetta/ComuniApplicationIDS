package unicam.cs.ids.punti;

import java.io.File;
import java.util.List;

/**
 * Classe per rappresentare un Punto di Interesse.
 */
public class PuntoInteresse extends Contenuto {

    public PuntoInteresse(String titolo, String testo, List<File> fileMultimediali, int id) {
        super(titolo, testo, fileMultimediali, id);
    }

}
