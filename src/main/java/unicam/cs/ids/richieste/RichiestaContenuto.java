package unicam.cs.ids.richieste;

import unicam.cs.ids.punti.Contenuto;
import unicam.cs.ids.punti.PuntoFisico;
import unicam.cs.ids.ruoli.GestoreComunale;

/**
 * Questa classe rappresenta una richiesta riguardante un contenuto.
 */
public class RichiestaContenuto implements RichiestaCommand {

    private final Contenuto contenuto;
    private final PuntoFisico puntoFisico;
    private final GestoreComunale gestoreComunale;

    public RichiestaContenuto(Contenuto contenuto, PuntoFisico puntoFisico, GestoreComunale gestoreComunale) {
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
    public String toString() {
        return "Richiesta Contenuto: " + contenuto.toString() + " - " + puntoFisico.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RichiestaContenuto richiesta)) return false;
        return richiesta.contenuto.equals(this.contenuto) && richiesta.puntoFisico.equals(this.puntoFisico);
    }

    @Override
    public int hashCode() {
        return contenuto.hashCode() + puntoFisico.hashCode();
    }
}
