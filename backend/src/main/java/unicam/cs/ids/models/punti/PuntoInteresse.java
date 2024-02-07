package unicam.cs.ids.models.punti;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Classe per rappresentare un Punto di Interesse.
 */
@Entity
@DiscriminatorValue("PuntoInteresse")
public class PuntoInteresse extends Contenuto {

    public PuntoInteresse(String titolo, String testo, List<MultipartFile> fileMultimediali) {
        super(titolo, testo, fileMultimediali);
    }

    public PuntoInteresse() {
        
    }

    @Override
    public String toString() {
        return "PuntoInteresse{" +
                super.toString() +
                '}';
    }

}
