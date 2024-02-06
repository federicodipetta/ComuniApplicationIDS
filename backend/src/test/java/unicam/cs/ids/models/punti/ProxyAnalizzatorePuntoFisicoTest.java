package unicam.cs.ids.models.punti;

import org.json.JSONException;
import org.junit.Test;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.servizi.ServizioOSM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProxyAnalizzatorePuntoFisicoTest {

    @Test
    public void controllaPuntoFisicoTest() throws JSONException, IOException {
        PuntoFisico puntoFisico = new PuntoFisico(new Coordinate(0.0, 0.0), new HashSet<>()); // Non ci interessa, per ora, che il comune sia al suo interno.
        PuntoFisico scuola = new PuntoFisico(new Coordinate(43.3019444, 13.730555555555556), new HashSet<>()); // Civitanova Marche.
        Comune comune = new Comune("Civitanova Marche", "Macerata", "1", puntoFisico);
        IAnalizzatorePuntoFisico analizzatorePuntoFisico = new ProxyAnalizzatorePuntoFisico(new AnalizzatorePuntoFisico(new ServizioOSM()));
        assertTrue(analizzatorePuntoFisico.controllaPuntoFisico(scuola, comune));
        assertTrue(analizzatorePuntoFisico.controllaPuntoFisico(scuola, comune));
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico, comune));
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico, comune));
        PuntoFisico puntoFisico2 = new PuntoFisico(new Coordinate(43.1397222, 13.068333333333333),new HashSet<>()); // Camerino.
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico2, comune));
        PuntoFisico puntoFisico3 = new PuntoFisico(new Coordinate(76.7841667, 68.99527777777777), new HashSet<>()); // Novaja Zemlja.
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico3, comune));
    }

}
