package unicam.cs.ids.richieste;

import unicam.cs.ids.punti.Contenuto;
import unicam.cs.ids.punti.PuntoFisico;
import unicam.cs.ids.ruoli.GestoreComunale;

/**
 * Questa classe rappresenta una richiesta riguardante un contenuto.
 */
public class RichiestaContenuto implements RichiestaCommand {

    private Contenuto contenuto;

    private PuntoFisico puntoFisico;

    private GestoreComunale gestoreComunale;

    /**
     * Costruttore di una richiesta riguardante un contenuto.
     * @param contenuto Il contenuto.
     * @param puntoFisico Il punto fisico del contenuto.
     * @param gestoreComunale Il gestore comunale.
     */
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
}
