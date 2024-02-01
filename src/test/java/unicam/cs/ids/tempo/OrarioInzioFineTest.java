package unicam.cs.ids.tempo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrarioInzioFineTest {
    private LocalDateTime inizio;
    private LocalDateTime fine;
    private OrarioInizioFine orarioInizioFine1;
    private LocalDateTime inizio2;
    private LocalDateTime fine2;
    private OrarioInizioFine orarioInizioFine2;
    private LocalDateTime inizio3;
    private LocalDateTime fine3;
    private OrarioInizioFine orarioInizioFine3;
    private LocalDateTime inizio4;
    private LocalDateTime fine4;
    private OrarioInizioFine orarioInizioFine4;
    @BeforeEach
    void setup(){
         inizio = LocalDateTime.of(2021, 1, 1, 0, 0);
         fine = LocalDateTime.of(2021, 1, 1, 1, 0);
         orarioInizioFine1 = new OrarioInizioFine(inizio, fine);
         inizio2 = LocalDateTime.of(2021, 1, 1, 0, 30);
         fine2 = LocalDateTime.of(2021, 1, 1, 1, 30);
         orarioInizioFine2 = new OrarioInizioFine(inizio2, fine2);
         inizio3 = LocalDateTime.of(2021, 1, 1, 1, 0);
         fine3 = LocalDateTime.of(2021, 1, 1, 2, 0);
         orarioInizioFine3 = new OrarioInizioFine(inizio3, fine3);
         inizio4 = LocalDateTime.of(2021, 1, 1, 3, 0);
         fine4 = LocalDateTime.of(2021, 1, 1, 4, 0);
         orarioInizioFine4 = new OrarioInizioFine(inizio4, fine4);
    }
    
    @Test
    public void testSovrapposto(){
        assertTrue(orarioInizioFine1.sovrapposto(orarioInizioFine2));
        assertTrue(orarioInizioFine2.sovrapposto(orarioInizioFine1));
        //testa che due orari che iniziano e finiscono nello stesso istante siano sovrapposti
        assertTrue(orarioInizioFine1.sovrapposto(orarioInizioFine3));
        assertTrue(orarioInizioFine3.sovrapposto(orarioInizioFine1));

        assertTrue(orarioInizioFine2.sovrapposto(orarioInizioFine3));
        assertTrue(orarioInizioFine3.sovrapposto(orarioInizioFine2));
        //testa che due orari non sovrapposti non siano sovrapposti
        assertFalse(orarioInizioFine1.sovrapposto(orarioInizioFine4));
        assertFalse(orarioInizioFine4.sovrapposto(orarioInizioFine1));
    }

    @Test
    public void testContiene(){
        assertTrue(orarioInizioFine1.contiene(inizio));
        assertTrue(orarioInizioFine1.contiene(fine));
        assertFalse(orarioInizioFine1.contiene(fine2));
    }


}
