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

public class RichiestaEliminaContenuto extends RichiestaAstratta {

    @JsonView({View.Dettagli.class})
    private final Contenuto contenuto;
    @JsonView({View.Dettagli.class})
    private final PuntoFisico puntoFisico;
    private final GestoreComunale gestoreComunale;

    public RichiestaEliminaContenuto(String id, Contenuto contenuto, PuntoFisico puntoFisico, GestoreComunale gestoreComunale) {
        super(id);
        this.contenuto = contenuto;
        this.puntoFisico = puntoFisico;
        this.gestoreComunale = gestoreComunale;
    }

    @Override
    public void esegui(boolean accetta) {
        if (accetta) {
            contenuto.setStato(Stato.ELIMINATO);
            gestoreComunale.eliminaContenuto(contenuto);
        }
    }

    @Override
    public JSONObject dettagliMinimi() {
        try {
            return new JSONObject().put("tipo", "eliminazione")
                    .put("contenuto", contenuto.dettagliMinimi());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public JSONObject dettagli() {
        try {
            return new JSONObject().put("tipo", "eliminazione")
                    .put("contenuto", contenuto.dettagli())
                    .put("puntoFisico", puntoFisico.toString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "Richiesta Eliminazione Contenuto: " + contenuto.toString() + " - " + puntoFisico.toString();
    }

}
