package unicam.cs.ids.models.ruoli;

import org.junit.Test;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.punti.Coordinate;
import unicam.cs.ids.models.punti.PuntoFisico;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

public class GestoreRuoliTest {

    private Utente utente1 = new Utente("utente1", "1");
    private Utente utente2 = new Utente("utente2", "2");
    private Comune civitanova = new Comune("Civitanova Marche", "MC", "1", new PuntoFisico(new Coordinate(43.308, 13.700), new ArrayList<>()));
    private Comune camerino = new Comune("Camerino", "MC", "2", new PuntoFisico(new Coordinate(43.135, 13.067), new ArrayList<>()));
    private RuoloComune ruoloComune1 = new RuoloComune(civitanova, Ruolo.TURISTA);
    private RuoloComune ruoloComune2 = new RuoloComune(camerino, Ruolo.CONTRIBUTOR_AUTORIZZATO);

    @Test
    public void testAggiungiUtenteRuoloComune() {
        GestoreRuoli gestoreRuoli = new GestoreRuoli();
        assert gestoreRuoli.setRuoloUtente(utente1, ruoloComune1);
        assert gestoreRuoli.setRuoloUtente(utente1, ruoloComune2);
        assertFalse(gestoreRuoli.setRuoloUtente(utente1, ruoloComune1));
        assert gestoreRuoli.setRuoloUtente(utente2, ruoloComune1);
        assert gestoreRuoli.setRuoloUtente(utente2, ruoloComune2);
        assertFalse(gestoreRuoli.setRuoloUtente(utente2, ruoloComune1));
    }

}
