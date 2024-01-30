package unicam.cs.ids.controllers;

import unicam.cs.ids.Comune;
import unicam.cs.ids.ruoli.GestorePiattaforma;

import java.util.Set;

public class ControllerComuni {

    private final GestorePiattaforma gestorePiattaforma;

    /**
     * Costruisce un ControllerComuni.
     */
    public ControllerComuni() {
        this.gestorePiattaforma = new GestorePiattaforma();
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
