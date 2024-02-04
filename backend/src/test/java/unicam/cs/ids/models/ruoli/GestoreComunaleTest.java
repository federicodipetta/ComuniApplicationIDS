package unicam.cs.ids.models.ruoli;

import org.junit.Test;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.punti.*;
import unicam.cs.ids.models.stato.Stato;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GestoreComunaleTest {

    @Test
    public void aggiungiContenutoTest() {
        GestoreComunale gc = new GestoreComunale(new Comune("Civitanova Marche", "MC", "0", new PuntoFisico(new Coordinate(43.308, 13.706), new ArrayList<>())));
        ContenutoBuilder builder = new ContenutoBuilder();
        Contenuto contenuto = builder.setId("Contenuto")
                .setTitolo("A")
                .setTesto("B")
                .setStato(Stato.APERTO)
                .build();
        PuntoFisico puntoFisico = new PuntoFisico(new Coordinate(43.308, 13.706), new ArrayList<>());
        assertTrue(gc.aggiungiContenuto(contenuto, puntoFisico));
        assertFalse(gc.getPuntiFisici().contains(puntoFisico));
        assertTrue(gc.aggiungiContenuto(contenuto, new PuntoFisico(new Coordinate(43.310, 13.708), new ArrayList<>())));
        assertTrue(gc.getPuntiFisici().contains(new PuntoFisico(new Coordinate(43.310, 13.708), new ArrayList<>())));
    }

}
