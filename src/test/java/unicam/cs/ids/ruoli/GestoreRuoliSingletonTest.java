package unicam.cs.ids.ruoli;

import org.junit.Test;
import unicam.cs.ids.Comune;
import unicam.cs.ids.punti.Coordinate;
import unicam.cs.ids.punti.PuntoFisico;

import static org.junit.Assert.assertFalse;

public class GestoreRuoliSingletonTest {

    private Utente utente1 = new Utente("utente1", "1");
    private Utente utente2 = new Utente("utente2", "2");
    private Comune civitanova = new Comune("Civitanova Marche", "MC", "1", new PuntoFisico(new Coordinate(43.308, 13.700)));
    private Comune camerino = new Comune("Camerino", "MC", "2", new PuntoFisico(new Coordinate(43.135, 13.067)));
    private RuoloComune ruoloComune1 = new RuoloComune(civitanova, Ruolo.TURISTA);
    private RuoloComune ruoloComune2 = new RuoloComune(camerino, Ruolo.CONTRIBUTOR_AUTORIZZATO);

    @Test
    public void testGetInstance() {
        GestoreRuoliSingleton gestoreRuoliSingleton = GestoreRuoliSingleton.getInstance();
        assert gestoreRuoliSingleton != null;
    }

    @Test
    public void testAggiungiUtenteRuoloComune() {
        GestoreRuoliSingleton gestoreRuoliSingleton = GestoreRuoliSingleton.getInstance();
        assert gestoreRuoliSingleton.aggiungiUtenteRuoloComune(utente1, ruoloComune1);
        assert gestoreRuoliSingleton.aggiungiUtenteRuoloComune(utente1, ruoloComune2);
        assertFalse(gestoreRuoliSingleton.aggiungiUtenteRuoloComune(utente1, ruoloComune1));
        assert gestoreRuoliSingleton.aggiungiUtenteRuoloComune(utente2, ruoloComune1);
        assert gestoreRuoliSingleton.aggiungiUtenteRuoloComune(utente2, ruoloComune2);
        assertFalse(gestoreRuoliSingleton.aggiungiUtenteRuoloComune(utente2, ruoloComune1));
    }

}
