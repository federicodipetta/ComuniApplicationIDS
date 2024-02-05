package unicam.cs.ids.dtos;

import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.Tempo;
import java.util.List;

public record ContenutoDto (

    String titolo,

     String testo,

    String id,

    String tempo,

    Stato stato,

    List<Contenuto> contenuti,

    List<Utente> iscritti,
    boolean ordianto){



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
    public List<Contenuto> getContenuti() {
        return contenuti;
    }

    public List<Utente> getIscritti() {
        return iscritti;
    }
}
