package unicam.cs.ids.servizi;

import org.junit.Test;
import unicam.cs.ids.punti.Coordinate;

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

}
