package unicam.cs.ids.richieste;

import org.json.JSONObject;
import unicam.cs.ids.punti.Contenuto;
import unicam.cs.ids.punti.PuntoFisico;
import unicam.cs.ids.ruoli.GestoreComunale;

public class RichiestaEliminaContenuto implements RichiestaCommand {

    private final Contenuto contenuto;
    private final PuntoFisico puntoFisico;
    private final GestoreComunale gestoreComunale;

    public RichiestaEliminaContenuto(Contenuto contenuto, PuntoFisico puntoFisico, GestoreComunale gestoreComunale) {
        this.contenuto = contenuto;
        this.puntoFisico = puntoFisico;
        this.gestoreComunale = gestoreComunale;
    }

    @Override
    public void esegui(boolean accetta) {
        if (accetta) {
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

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RichiestaEliminaContenuto richiesta)) return false;
        return richiesta.contenuto.equals(this.contenuto) && richiesta.puntoFisico.equals(this.puntoFisico);
    }

    @Override
    public int hashCode() {
        return contenuto.hashCode() + puntoFisico.hashCode();
    }

}
