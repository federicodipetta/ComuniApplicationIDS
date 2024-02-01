package unicam.cs.ids.punti;

import org.junit.Test;
import unicam.cs.ids.stato.Stato;
import unicam.cs.ids.tempo.InsiemeOrari;
import unicam.cs.ids.tempo.OrarioInizioFine;
import unicam.cs.ids.tempo.SempreAttivo;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContenutoBuilderTest {

    @Test
    public void buildItinerarioTest() {
        ContenutoBuilder contenutoBuilder = new ContenutoBuilder();
        contenutoBuilder.setStato(Stato.APERTO).setContenuto(new PuntoInteresse("titolo", "testo", null, "0"))
                .setTitolo("titolo").setTesto("testo").setId("0");
        assertInstanceOf(Itinerario.class, contenutoBuilder.build());
        assertEquals(contenutoBuilder.build(), new Itinerario("titolo", "testo", null, List.of(new PuntoInteresse("titolo", "testo", null, "0")), "0"));
    }
    @Test
    public void buildPuntoInteresseTest() {
        ContenutoBuilder contenutoBuilder = new ContenutoBuilder();
        contenutoBuilder.setStato(Stato.APERTO)
                .setTitolo("titolo")
                .setTesto("testo")
                .setId("0");
        assertInstanceOf(PuntoInteresse.class, contenutoBuilder.build());
        assertEquals(contenutoBuilder.build(), new PuntoInteresse("titolo", "testo", null, "0"));
    }

    @Test
    public void buildPuntoInteresseTest2() {
        ContenutoBuilder contenutoBuilder = new ContenutoBuilder();
        contenutoBuilder.setStato(Stato.APERTO)
                .setTitolo("titolo")
                .setTesto("testo")
                .setId("0")
                .setTempo(new SempreAttivo());
        assertInstanceOf(PuntoInteresse.class, contenutoBuilder.build());
        assertEquals(contenutoBuilder.build(), new PuntoInteresse("titolo", "testo", null, "0"));
    }

    @Test
    public void buildEventoTest(){
        ContenutoBuilder contenutoBuilder = new ContenutoBuilder();
        contenutoBuilder.setStato(Stato.APERTO)
                .setTitolo("titolo")
                .setTesto("testo")
                .setId("0")
                .setTempo(new InsiemeOrari(List.of(new OrarioInizioFine(LocalDateTime.now(), LocalDateTime.now()))));
        assertInstanceOf(Evento.class, contenutoBuilder.build());
        assertEquals(contenutoBuilder.build(), new Evento("titolo", "testo", null, "0", new InsiemeOrari(List.of(new OrarioInizioFine(LocalDateTime.now(), LocalDateTime.now())))));
    }



}
