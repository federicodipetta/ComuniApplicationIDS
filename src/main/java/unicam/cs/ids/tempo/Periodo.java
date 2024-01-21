package unicam.cs.ids.tempo;

import java.time.LocalDateTime;
import java.util.function.Function;

public class Periodo implements Tempo{

    private Function<LocalDateTime,OrarioInizioFine> adjuster;

    public Periodo(Function<LocalDateTime,OrarioInizioFine>adjuster){
        this.adjuster = adjuster;
    }

    @Override
    public boolean attivato(LocalDateTime orario) {
        return adjuster.apply(orario).contiene(orario);
    }

    @Override
    public OrarioInizioFine getProssimoOrario(LocalDateTime ora) {
        return adjuster.apply(ora);
    }
}
