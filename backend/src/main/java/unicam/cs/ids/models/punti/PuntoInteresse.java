package unicam.cs.ids.models.punti;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Classe per rappresentare un Punto di Interesse.
 */
public class PuntoInteresse extends Contenuto {

    public PuntoInteresse(String titolo, String testo, List<MultipartFile> fileMultimediali, String id) {
        super(id,titolo, testo, fileMultimediali);
    }

    @Override
    public String toString() {
        return "PuntoInteresse{" +
                super.toString() +
                '}';
    }

}
