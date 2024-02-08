package unicam.cs.ids.models.punti;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.ruoli.Utente;

@Entity
public class Iscrizione {
    @EmbeddedId
    private IscrizioneKey id;
    @Transient
    private  MultipartFile file;

    private String descrizione;

    private int punti;

    public int getPunti() {
        return punti;
    }

    public void addPunti(int punti){
        this.punti += punti;
    }

    public Iscrizione(Utente iscritto, Contest contest, MultipartFile file) {
        this.id = new IscrizioneKey(iscritto, contest);
    }
    public Iscrizione(Utente iscritto, Contest contest, String descrizione) {
        this.id = new IscrizioneKey(iscritto, contest);
        this.descrizione = descrizione;
    }
    public Iscrizione() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Iscrizione that)) return false;
        return id.getIscritto().equals(that.id.getIscritto()) && id.getContest().equals(that.id.getContest());
    }

    @Override
    public int hashCode() {
        return id.getIscritto().hashCode() + id.getContest().hashCode();
    }

    @Override
    public String toString() {
        return "Iscrizione{" +
                "iscritto=" + id.getIscritto() +
                ", contest=" + id.getContest() +
                ", file=" + file +
                '}';
    }

}
