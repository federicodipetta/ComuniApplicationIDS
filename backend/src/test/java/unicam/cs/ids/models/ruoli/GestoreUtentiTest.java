package unicam.cs.ids.models.ruoli;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.punti.Coordinate;
import unicam.cs.ids.models.punti.PuntoFisico;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;

public class GestoreUtentiTest {

    private GestoreUtenti gestoreUtenti = new GestoreUtenti(GestorePiattaforma.getInstance().getUtentiRepository());
    private Utente utente1;
    private Utente utente2;
    private Comune civitanova;
    private Comune camerino;
    private RuoloComune ruoloComune1;
    private RuoloComune ruoloComune2;

    @BeforeEach
    public void inizializzazioneVariabili() {
        Utente utente1 = new Utente("utente1");
        Utente utente2 = new Utente("utente2");
        Comune civitanova = new Comune("Civitanova Marche", "MC", new PuntoFisico(new Coordinate(43.308, 13.700), new HashSet<>()));
        Comune camerino = new Comune("Camerino", "MC", new PuntoFisico(new Coordinate(43.135, 13.067), new HashSet<>()));
        RuoloComune ruoloComune1 = new RuoloComune(civitanova, Ruolo.TURISTA);
        RuoloComune ruoloComune2 = new RuoloComune(camerino, Ruolo.CONTRIBUTOR_AUTORIZZATO);
    }

    @Test
    public void testAggiungiUtente() {
        assert gestoreUtenti.aggiungiUtente(utente1);
        assertFalse(gestoreUtenti.aggiungiUtente(utente1));
        assert (gestoreUtenti.getGestoreRuoli().getComuniAbilitati(utente1, Ruolo.TURISTA).isEmpty());
    }

    @Test
    public void testSetRuoloUtente() {
        Utente utente1 = new Utente("utente1");
        Comune civitanova = new Comune("Civitanova Marche", "MC", new PuntoFisico(new Coordinate(43.308, 13.700), new HashSet<>()));
        RuoloComune ruoloComune1 = new RuoloComune(civitanova, Ruolo.TURISTA);
        gestoreUtenti.setRuoloUtente(utente1, ruoloComune1);
        assert (gestoreUtenti.getGestoreRuoli().getComuniAbilitati(utente1, Ruolo.TURISTA).contains(civitanova) && gestoreUtenti.getGestoreRuoli().getComuniAbilitati(utente1, Ruolo.TURISTA).size() == 1);

    }

}
