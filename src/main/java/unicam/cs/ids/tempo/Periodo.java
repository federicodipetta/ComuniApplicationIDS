package unicam.cs.ids.tempo;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Periodo implements Tempo{
    private TemporalAdjuster inizio;
    private TemporalAdjuster fine;

    public Periodo(TemporalAdjuster inizio, TemporalAdjuster fine){
        this.inizio = inizio;
        this.fine = fine;
    }

    @Override
    public boolean attivato(LocalDateTime orario) {
        return orario.with(inizio).isBefore(orario) && orario.with(fine).isAfter(orario);
    }

    @Override
    public OrarioInizioFine getProssimoOrario(LocalDateTime ora) {
        return new OrarioInizioFine(ora.with(inizio), ora.with(fine));
    }
}
