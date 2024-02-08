package unicam.cs.ids.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.cs.ids.configurazioni.GestorePiattaformaBuilder;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;

import java.util.Set;

@Service
public class ControllerComuni {

    private GestorePiattaforma gestorePiattaforma;


    /**
     * Costruisce un ControllerComuni.
     */
    @Autowired
    public ControllerComuni(GestorePiattaformaBuilder builder) {
        this.gestorePiattaforma = GestorePiattaforma.getInstance(builder);
    }

    /**
     * Costruisce un ControllerComuni.
     * @param gestorePiattaforma Il gestore della piattaforma.
     */
    public ControllerComuni(GestorePiattaforma gestorePiattaforma) {
        this.gestorePiattaforma = gestorePiattaforma;
    }


    /**
     * Metodo utilizzato per aggiungere un comune al controller.
     * @param comune il comune da aggiungere.
     * @return true se il comune è stato aggiunto, false altrimenti.
     */
    public boolean aggiungiComune(Comune comune) {
        if (comune.nome().isBlank() || comune.provincia().isBlank())
            return false;
        return gestorePiattaforma.aggiungiComune(comune);
    }


    /**
     * Metodo utilizzato per ottenere i comuni del Controller.
     * @return Un insieme contenente i comuni del Controller.
     */
    public Set<Comune> getComuni() {
        return gestorePiattaforma.getComuni();
    }

}
