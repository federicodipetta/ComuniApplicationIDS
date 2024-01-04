package unicam.cs.ids.tempo;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PeriodoTest {
    @Test
    public void testAttivato() {
        Periodo p = new Periodo(
                TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY),
                TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)
        );
        assertTrue(p.attivato(LocalDateTime.of(2024,1,6,0,0)));
    }

}
