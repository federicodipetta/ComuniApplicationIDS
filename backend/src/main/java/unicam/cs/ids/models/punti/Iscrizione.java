package unicam.cs.ids.models.punti;

import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.ruoli.Utente;

import java.io.File;

public class Iscrizione {

    private final Utente iscritto;
    private final Contest contest;
    private final MultipartFile file;

    public Iscrizione(Utente iscritto, Contest contest, MultipartFile file) {
        this.iscritto = iscritto;
        this.contest = contest;
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Iscrizione that)) return false;
        return iscritto.equals(that.iscritto) && contest.equals(that.contest);
    }

    @Override
    public int hashCode() {
        return iscritto.hashCode() + contest.hashCode();
    }

    @Override
    public String toString() {
        return "Iscrizione{" +
                "iscritto=" + iscritto +
                ", contest=" + contest.toString() +
                ", file=" + file +
                '}';
    }

}
