package unicam.cs.ids.models.controllers;

import org.junit.Test;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.punti.ContenutoBuilder;
import unicam.cs.ids.models.punti.Coordinate;
import unicam.cs.ids.models.punti.PuntoFisico;
import unicam.cs.ids.models.stato.Stato;

import java.util.ArrayList;

public class ElementiControllerTest {

    @Test
    public void testAggiungiContenuto() {
        Comune comune = new Comune("Milano", "Milano", "0",
                new PuntoFisico(new Coordinate( 45.4641945, 9.1896349), new ArrayList<>()));
        ControllerElementi controllerElementi = new ControllerElementi();
        ContenutoBuilder builder = new ContenutoBuilder();
        Contenuto contenuto = builder
                .setTitolo("titolo")
                .setTesto("testo")
                .setId("1")
                .setStato(Stato.APERTO)
                .build();
        new ControllerComuni().aggiungiComune(comune);
        controllerElementi.aggiungiContenuto(contenuto, "0", comune.puntoComune());
    }

}
