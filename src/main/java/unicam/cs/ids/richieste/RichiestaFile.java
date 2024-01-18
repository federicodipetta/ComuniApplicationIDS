package unicam.cs.ids.richieste;

import unicam.cs.ids.punti.Contenuto;

import java.io.File;
import java.util.List;

public class RichiestaFile implements RichiestaCommand {

    private List<File> files;

    private Contenuto contenuto;

    /**
     * Costruttore di una richiesta riguardante un file.
     * @param files I file.
     * @param contenuto Il contenuto.
     */
    public RichiestaFile(List<File> files, Contenuto contenuto) {
        this.files = files;
        this.contenuto = contenuto;
    }

    @Override
    public void esegui(boolean accetta) {
        if(accetta) {
            contenuto.aggiungiFile(files);
        }
    }
}
