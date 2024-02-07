package unicam.cs.ids.models.punti;

import org.junit.Test;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.InsiemeOrari;
import unicam.cs.ids.models.tempo.OrarioInizioFine;
import unicam.cs.ids.models.tempo.SempreAttivo;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContenutoBuilderTest {

    @Test
    public void buildItinerarioTest() {
        ContenutoBuilder contenutoBuilder = new ContenutoBuilder();
        contenutoBuilder.setStato(Stato.APERTO).setContenuto(
                new PuntoInteresse("titolo", "testo", null), true)
                .setTitolo("titolo").setTesto("testo").setId("0");
        assertInstanceOf(Itinerario.class, contenutoBuilder.build());
        assertTrue(contenutoBuilder.build() instanceof Itinerario);
        assertEquals(contenutoBuilder.build(), new Itinerario("titolo", "testo", null, List.of(new PuntoInteresse("titolo", "testo", null)), new SempreAttivo()));
    }
    @Test
    public void buildPuntoInteresseTest() {
        ContenutoBuilder contenutoBuilder = new ContenutoBuilder();
        contenutoBuilder.setStato(Stato.APERTO)
                .setTitolo("titolo")
                .setTesto("testo")
                .setId("0");
        assertInstanceOf(PuntoInteresse.class, contenutoBuilder.build());
        assertTrue(contenutoBuilder.build() instanceof PuntoInteresse);

        assertEquals(contenutoBuilder.build(), new PuntoInteresse("titolo", "testo", null));
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
        assertTrue(contenutoBuilder.build() instanceof PuntoInteresse);
        assertEquals(contenutoBuilder.build(), new PuntoInteresse("titolo", "testo", null));
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
        assertTrue(contenutoBuilder.build() instanceof Evento);
        assertEquals(contenutoBuilder.build(), new Evento("titolo", "testo", null, new InsiemeOrari(List.of(new OrarioInizioFine(LocalDateTime.now(), LocalDateTime.now())))));
    }



}
