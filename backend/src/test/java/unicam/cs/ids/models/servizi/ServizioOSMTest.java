package unicam.cs.ids.models.servizi;

import org.json.JSONException;
import org.junit.Test;
import unicam.cs.ids.models.punti.Coordinate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServizioOSMTest {

    @Test
    public void getInfoPuntoTest() throws IOException {
        ServizioOSM servizioOSM = new ServizioOSM();
        Coordinate coordinate = new Coordinate(43.3019444, 13.730555555555556);
        String citta = servizioOSM.getInfoPunto(coordinate)
                .split("town")[1]
                .split(":")[1]
                .split(",")[0];
        assertEquals("\"Civitanova Marche\"", citta);
    }

    @Test
    public void getCoordinateTest() throws IOException, JSONException {
        ServizioOSM servizioOSM = new ServizioOSM();
        Coordinate coordinate = servizioOSM.getCoordinate("Civitanova Marche");
        assertTrue(coordinate.latitudine() > 43.3 && coordinate.latitudine() < 43.4);
        assertTrue(coordinate.longitudine() > 13.7 && coordinate.longitudine() < 13.8);
        coordinate = servizioOSM.getCoordinate("cAmErInO");
        assertTrue(coordinate.latitudine() > 43.1 && coordinate.latitudine() < 43.2);
        assertTrue(coordinate.longitudine() > 13.0 && coordinate.longitudine() < 13.1);
    }

}
