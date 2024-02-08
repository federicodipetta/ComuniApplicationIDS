package unicam.cs.ids.models.punti;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import unicam.cs.ids.models.ruoli.Utente;

import java.io.Serializable;

@Embeddable
public class IscrizioneKey implements Serializable {
    @ManyToOne
    private Utente iscritto;
    @ManyToOne
    private Contest contest;

    public IscrizioneKey(Utente iscritto, Contest contest) {
        this.iscritto = iscritto;
        this.contest = contest;
    }

    public IscrizioneKey() {
    }

    public Utente getIscritto() {
        return iscritto;
    }

    public void setIscritto(Utente iscritto) {
        this.iscritto = iscritto;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }
}
