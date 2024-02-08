package unicam.cs.ids.dtos;

import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.InsiemeOrari;
import unicam.cs.ids.models.tempo.OrarioInizioFine;
import unicam.cs.ids.models.tempo.SempreAttivo;
import unicam.cs.ids.models.tempo.TempoAstratto;

import java.util.List;

public record ContenutoDto (

    String titolo,

    String testo,

    String id,

    List<Coppia> tempo,

    Stato stato,

    List<Contenuto> contenuti,

    List<Utente> iscritti,
    boolean ordinato){



    public String getTitolo() {
        return titolo;
    }

    public String getTesto() {
        return testo;
    }

    public String getId() {
        return id;
    }

    public TempoAstratto getTempo() {
        List<OrarioInizioFine> lista = tempo.stream().map(Coppia::serializzazione).toList();
        if (lista.isEmpty())
            return new SempreAttivo();
        return new InsiemeOrari(lista);
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
