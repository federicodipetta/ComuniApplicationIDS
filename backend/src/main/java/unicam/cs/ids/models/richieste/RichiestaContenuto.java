package unicam.cs.ids.models.richieste;

import org.json.JSONObject;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.punti.PuntoFisico;
import unicam.cs.ids.models.ruoli.GestoreComunale;

/**
 * Questa classe rappresenta una richiesta riguardante un contenuto.
 */
public class RichiestaContenuto extends RichiestaAstratta {

    private final Contenuto contenuto;
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
            gestoreComunale.aggiungiContenuto(contenuto, puntoFisico);
        }
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
