package unicam.cs.ids.models.punti;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.view.View;

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
