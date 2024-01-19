package unicam.cs.ids.richieste;

import unicam.cs.ids.punti.Contest;
import unicam.cs.ids.ruoli.Utente;

import java.io.File;

/**
 * Questa classe rappresenta una richiesta riguardante un'iscrizione.
 */
public class RichiestaIscrizione implements RichiestaCommand {

    private final File file;
    private final Utente utente;
    private final Contest contest;

    public RichiestaIscrizione(File file, Contest contest, Utente utente) {
        this.file = file;
        this.contest = contest;
        this.utente = utente;
    }

    @Override
    public void esegui(boolean accetta) {
        if (accetta) {
            contest.aggiungiIscrizione(utente, file);
        }
    }

    @Override
    public String toString() {
        return "RichiestaIscrizione{" +
                "file=" + file.toString() +
                ", " + utente.toString() +
                ", " + contest.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RichiestaIscrizione that)) return false;
        return file.equals(that.file) && utente.equals(that.utente) && contest.equals(that.contest);
    }

    @Override
    public int hashCode() {
        return file.hashCode() + utente.hashCode() + contest.hashCode();
    }

}
