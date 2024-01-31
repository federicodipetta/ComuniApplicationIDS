package unicam.cs.ids.richieste;

import org.json.JSONObject;
import unicam.cs.ids.punti.Contenuto;

import java.io.File;
import java.util.List;

/**
 * Questa classe rappresenta una richiesta riguardante un file.
 */
public class RichiestaFile implements RichiestaCommand {

    private final List<File> files;
    private final Contenuto contenuto;

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

    @Override
    public JSONObject dettagliMinimi() {
        try {
            return new JSONObject().put("tipo", "caricamento file")
                    .put("file", files.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public JSONObject dettagli() {
        try {
            return new JSONObject().put("tipo", "caricamento file")
                    .put("contenuto", contenuto.dettagli())
                    .put("files", files.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "Richiesta File: " + contenuto.toString() + " - " + files.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RichiestaFile richiesta)) return false;
        return richiesta.contenuto.equals(this.contenuto) && richiesta.files.equals(this.files);
    }

    @Override
    public int hashCode() {
        return contenuto.hashCode() + files.hashCode();
    }
}
