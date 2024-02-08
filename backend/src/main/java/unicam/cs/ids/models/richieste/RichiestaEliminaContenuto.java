package unicam.cs.ids.models.richieste;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import org.json.JSONObject;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.punti.PuntoFisico;
import unicam.cs.ids.models.ruoli.GestoreComunale;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.view.View;
@Entity
@DiscriminatorValue("RichiestaEliminaContenuto")
public class RichiestaEliminaContenuto extends RichiestaAstratta {

    @JsonView({View.Dettagli.class})
    @OneToOne
    private Contenuto contenuto;

    @JsonView({View.Dettagli.class})
    @OneToOne
    private PuntoFisico puntoFisico;

    @Transient
    private GestoreComunale gestoreComunale;

    public RichiestaEliminaContenuto(String id, Contenuto contenuto, PuntoFisico puntoFisico) {
        super();
        this.contenuto = contenuto;
        this.puntoFisico = puntoFisico;
      ;
    }
  
    public RichiestaEliminaContenuto() { }

    @Override
    public void esegui(boolean accetta) {
        this.gestoreComunale = GestorePiattaforma.getInstance().getGestoreComuni().getGestoreComunale(
                GestorePiattaforma.getInstance().getGestoreComuni().getComuneById(super.getIdc()));
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
