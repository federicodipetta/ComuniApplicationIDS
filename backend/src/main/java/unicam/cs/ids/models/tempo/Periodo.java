package unicam.cs.ids.models.tempo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.time.LocalDateTime;
import java.util.function.Function;
@Entity
@DiscriminatorValue("Periodo")
public class Periodo extends TempoAstratto{
    @Transient
    private Function<LocalDateTime,OrarioInizioFine> adjuster;

    public Periodo(Function<LocalDateTime,OrarioInizioFine>adjuster){
        this.adjuster = adjuster;
    }

    public Periodo() {

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
