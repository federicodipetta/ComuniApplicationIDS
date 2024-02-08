package unicam.cs.ids.dtos;

import unicam.cs.ids.models.punti.Iscrizione;
import unicam.cs.ids.models.punti.PuntoFisico;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.TempoAstratto;

import java.util.Map;

public class ContestDto {

    private final String id;
    private final Utente animatore;
    private final Map<Iscrizione, Integer> iscrizioni;
    private final String titolo;
    private final String descrizione;
    private TempoAstratto tempo;
    private Stato stato;
    private final PuntoFisico puntoFisico;

    public ContestDto(String id, Utente animatore, Map<Iscrizione, Integer> iscrizioni, String titolo, String descrizione, TempoAstratto tempo, Stato stato, PuntoFisico puntoFisico) {
        this.id = id;
        this.animatore = animatore;
        this.iscrizioni = iscrizioni;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.tempo = tempo;
        this.stato = stato;
        this.puntoFisico = puntoFisico;
    }

    public TempoAstratto getTempo() {
        return tempo;
    }

    public void setTempo(TempoAstratto tempo) {
        this.tempo = tempo;
    }

    public String getId() {
        return id;
    }

    public Utente getAnimatore() {
        return animatore;
    }

    public Map<Iscrizione, Integer> getIscrizioni() {
        return iscrizioni;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public PuntoFisico getPuntoFisico() {
        return puntoFisico;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}
