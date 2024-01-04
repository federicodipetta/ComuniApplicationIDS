package unicam.cs.ids.tempo;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InsiemeOrariTest {
    @Test
    public void testBuild() {
        InsiemeOrari insiemeOrari = new InsiemeOrari(List.of(
                new OrarioInizioFine(
                        LocalDateTime.of(2021, 1,1,5,0),
                        LocalDateTime.of(2021, 1,1,6,0)),
                new OrarioInizioFine(
                        LocalDateTime.of(2021, 1,1,3,0),
                        LocalDateTime.of(2021, 1,1,4,0)),
                new OrarioInizioFine(
                        LocalDateTime.of(2021, 1,1,3,1),
                        LocalDateTime.of(2021, 1,1,3,30))

        ));
        //controllo che l'insieme sia compresso e ordinato
        assertEquals(2, insiemeOrari.getOrarioInizioFineList().size());
        assertEquals(LocalDateTime.of(2021, 1,1,3,0), insiemeOrari.getOrarioInizioFineList().get(0).inizio());
        assertEquals(LocalDateTime.of(2021, 1,1,4,0), insiemeOrari.getOrarioInizioFineList().get(0).fine());
        assertEquals(LocalDateTime.of(2021, 1,1,5,0), insiemeOrari.getOrarioInizioFineList().get(1).inizio());
    }
}
