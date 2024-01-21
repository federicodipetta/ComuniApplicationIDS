package unicam.cs.ids.richieste;


import org.junit.Test;
import unicam.cs.ids.Comune;
import unicam.cs.ids.punti.Contenuto;
import unicam.cs.ids.punti.PuntoInteresse;
import unicam.cs.ids.ruoli.GestoreComunale;
import unicam.cs.ids.stato.Stato;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class SegnalazioneTest {
    @Test
    public void testEsegui() {
        Contenuto contenuto = new PuntoInteresse("nome", "questo punto cerrà segnalato a breve", new ArrayList<>(),1);
        Segnalazione segnalazione = new Segnalazione("testo",contenuto);
        segnalazione.esegui(true);
        assertEquals(contenuto.getStato(), Stato.ELIMINATO);
    }
}
