package unicam.cs.ids.models.punti;

import org.json.JSONException;
import org.junit.Test;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.servizi.ServizioOSM;

import java.io.IOException;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnalizzatorePuntoFisicoTest {

    @Test
    public void controllaPuntoFisicoTest() throws IOException, JSONException {
        PuntoFisico puntoFisico = new PuntoFisico(new Coordinate(0.0, 0.0),new HashSet<>()); // Non ci interessa, per ora, che il comune sia al suo interno.
        PuntoFisico scuola = new PuntoFisico(new Coordinate(43.3019444, 13.730555555555556), new HashSet<>()); // Civitanova Marche.
        Comune comune = new Comune("Civitanova Marche", "Macerata", puntoFisico);
        IAnalizzatorePuntoFisico analizzatorePuntoFisico = new AnalizzatorePuntoFisico(new ServizioOSM());
        assertTrue(analizzatorePuntoFisico.controllaPuntoFisico(scuola, comune));
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico, comune));
        PuntoFisico puntoFisico2 = new PuntoFisico(new Coordinate(43.1397222, 13.068333333333333), new HashSet<>()); // Camerino.
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico2, comune));
        PuntoFisico puntoFisico3 = new PuntoFisico(new Coordinate(76.7841667, 68.99527777777777), new HashSet<>()); // Novaja Zemlja.
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico3, comune));
    }

    @Test
    public void controllaPuntoFisicoTest2() throws IOException, JSONException {
        PuntoFisico puntoFisico1 = new PuntoFisico(new Coordinate(43.7725, 11.256666666666666),new HashSet<>()); // Firenze.
        IAnalizzatorePuntoFisico analizzatorePuntoFisico = new AnalizzatorePuntoFisico(new ServizioOSM());
        Comune firenze = new Comune("Firenze", "Firenze", puntoFisico1);
        assertTrue(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico1, firenze));
        PuntoFisico puntoFisico2 = new PuntoFisico(new Coordinate(41.8908333, 12.492777777777777),new HashSet<>()); // Roma.
        Comune roma = new Comune("Roma", "Roma", puntoFisico2);
        assertTrue(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico2, roma));
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico1, roma));
    }

}
