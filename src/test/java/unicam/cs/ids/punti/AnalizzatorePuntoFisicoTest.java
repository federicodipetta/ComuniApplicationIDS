package unicam.cs.ids.punti;

import org.json.JSONException;
import org.junit.Test;
import unicam.cs.ids.Comune;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnalizzatorePuntoFisicoTest {

    @Test
    public void controllaPuntoFisicoTest() throws IOException, JSONException {
        PuntoFisico puntoFisico = new PuntoFisico(new Coordinate(0.0, 0.0), new ArrayList<>()); // Non ci interessa, per ora, che il comune sia al suo interno.
        PuntoFisico scuola = new PuntoFisico(new Coordinate(43.3019444, 13.730555555555556), new ArrayList<>()); // Civitanova Marche.
        Comune comune = new Comune("Civitanova Marche", "Macerata", "1", puntoFisico);
        AnalizzatorePuntoFisico analizzatorePuntoFisico = new AnalizzatorePuntoFisico();
        assertTrue(analizzatorePuntoFisico.controllaPuntoFisico(scuola, comune));
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico, comune));
        PuntoFisico puntoFisico2 = new PuntoFisico(new Coordinate(43.1397222, 13.068333333333333), new ArrayList<>()); // Camerino.
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico2, comune));
        PuntoFisico puntoFisico3 = new PuntoFisico(new Coordinate(76.7841667, 68.99527777777777), new ArrayList<>()); // Novaja Zemlja.
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico3, comune));
    }

    @Test
    public void controllaPuntoFisicoTest2() throws IOException, JSONException {
        PuntoFisico puntoFisico1 = new PuntoFisico(new Coordinate(43.7725, 11.256666666666666), new ArrayList<>()); // Firenze.
        AnalizzatorePuntoFisico analizzatorePuntoFisico = new AnalizzatorePuntoFisico();
        Comune firenze = new Comune("Firenze", "Firenze", "1", puntoFisico1);
        assertTrue(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico1, firenze));
        PuntoFisico puntoFisico2 = new PuntoFisico(new Coordinate(41.8908333, 12.492777777777777), new ArrayList<>()); // Roma.
        Comune roma = new Comune("Roma", "Roma", "1", puntoFisico2);
        assertTrue(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico2, roma));
        assertFalse(analizzatorePuntoFisico.controllaPuntoFisico(puntoFisico1, roma));
    }

}
