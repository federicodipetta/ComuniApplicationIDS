package unicam.cs.ids.dtos;

import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.Tempo;
import java.util.List;

public class ContenutoDto {

    private final String titolo;

    private final String testo;

    private final String id;

    private final String tempo;

    private Stato stato;

    private final List<Contenuto> contenuti;

    private final List<Utente> iscritti;

    public ContenutoDto(String titolo, String testo, String id, String tempo, Stato stato, List<Contenuto> contenuti, List<Utente> iscritti) {
        this.titolo = titolo;
        this.testo = testo;
        this.id = id;
        this.tempo = tempo;
        this.stato = stato;
        this.contenuti = contenuti;
        this.iscritti = iscritti;
    }


    public String getTitolo() {
        return titolo;
    }

    public String getTesto() {
        return testo;
    }

    public String getId() {
        return id;
    }

    public String getTempo() {
        return tempo;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public List<Contenuto> getContenuti() {
        return contenuti;
    }

    public List<Utente> getIscritti() {
        return iscritti;
    }
}
