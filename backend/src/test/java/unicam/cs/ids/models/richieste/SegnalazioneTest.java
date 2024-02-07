package unicam.cs.ids.models.richieste;


import org.junit.Test;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.punti.PuntoInteresse;
import unicam.cs.ids.models.stato.Stato;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
public class SegnalazioneTest {
    @Test
    public void testEsegui() {
        Contenuto contenuto = new PuntoInteresse("nome", "questo punto cerrà segnalato a breve", new ArrayList<>());
        Segnalazione segnalazione = new Segnalazione("0","testo",contenuto);
        segnalazione.esegui(true);
        assertEquals(contenuto.getStato(), Stato.ELIMINATO);
    }
}
