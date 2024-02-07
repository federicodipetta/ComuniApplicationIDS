package unicam.cs.ids.models.richieste;

import com.fasterxml.jackson.annotation.JsonView;
import org.json.JSONObject;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.punti.PuntoFisico;
import unicam.cs.ids.models.ruoli.GestoreComunale;
import unicam.cs.ids.models.stato.SelettoreStato;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.view.View;

import java.time.LocalDateTime;

/**
 * Questa classe rappresenta una richiesta riguardante un contenuto.
 */
public class RichiestaContenuto extends RichiestaAstratta {

    @JsonView({View.Dettagli.class})
    private final Contenuto contenuto;
    @JsonView({View.Dettagli.class})
    private final PuntoFisico puntoFisico;
    private final GestoreComunale gestoreComunale;

    public RichiestaContenuto(String id, Contenuto contenuto, PuntoFisico puntoFisico, GestoreComunale gestoreComunale) {
        super(id);
        this.contenuto = contenuto;
        this.puntoFisico = puntoFisico;
        this.gestoreComunale = gestoreComunale;
    }

    @Override
    public void esegui(boolean accetta) {
        if (accetta) {
            contenuto.setStato(SelettoreStato.nuovoStato(Stato.APERTO, contenuto.getTempo(), LocalDateTime.now()));
            gestoreComunale.aggiungiContenuto(contenuto, puntoFisico);
        }
        contenuto.setStato(Stato.ELIMINATO);
    }

    @Override
    public JSONObject dettagliMinimi() {
        try {
            return new JSONObject().put("tipo", "aggiunta")
                    .put("contenuto", contenuto.dettagliMinimi());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public JSONObject dettagli() {
        try {
            return new JSONObject().put("tipo", "aggiunta")
                    .put("contenuto", contenuto.dettagli())
                    .put("puntoFisico", puntoFisico.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RichiestaContenuto richiesta)) return false;
        return richiesta.contenuto.equals(this.contenuto) && richiesta.puntoFisico.equals(this.puntoFisico);
    }

    public Contenuto getContenuto() {
        return contenuto;
    }

    public PuntoFisico getPuntoFisico() {
        return puntoFisico;
    }

    public GestoreComunale getGestoreComunale() {
        return gestoreComunale;
    }

}
