package unicam.cs.ids.richieste;

import org.json.JSONObject;
import unicam.cs.ids.punti.Contenuto;
import unicam.cs.ids.punti.PuntoFisico;
import unicam.cs.ids.ruoli.GestoreComunale;

public class RichiestaEliminaContenuto extends RichiestaAstratta {

    private final Contenuto contenuto;
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
