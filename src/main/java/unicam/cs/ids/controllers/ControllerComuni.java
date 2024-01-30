package unicam.cs.ids.controllers;

import unicam.cs.ids.Comune;

import java.util.HashSet;
import java.util.Set;

public class ControllerComuni {

    private final Set<Comune> comuni;

    /**
     * Costruisce un ControllerComuni.
     */
    public ControllerComuni() {
        this.comuni = new HashSet<>();
    }

    /**
     * Costruisce un ControllerComuni a partire da un insieme di comuni.
     * @param comuni L'insieme di partenza.
     */
    public ControllerComuni(Set<Comune> comuni) {
        this.comuni = comuni;
    }

    /**
     * Metodo utilizzato per aggiungere un comune al controller.
     * @param comune il comune da aggiungere.
     * @return true se il comune è stato aggiunto, false altrimenti.
     */
    public boolean aggiungiComune(Comune comune) {
        return comuni.add(comune);
    }

    /**
     * Metodo utilizzato per ottenere i comuni del Controller.
     * @return Un insieme contenente i comuni del Controller.
     */
    public Set<Comune> getComuni() {
        return comuni;
    }

}
