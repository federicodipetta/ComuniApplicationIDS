package unicam.cs.ids.models.tempo;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import unicam.cs.ids.view.View;

import java.time.LocalDateTime;

/**
 * Questa classe rappresenta un tempo sempre attivo specificatamente
 * per i contenuti che non hanno un tempo di attivazione come i punti di interesse.
 */
@Entity
@DiscriminatorValue("SempreAttivo")
public class SempreAttivo extends TempoAstratto {

    @Override
    public boolean attivato(LocalDateTime orario) {
        return true;
    }

    @Override
    public OrarioInizioFine getProssimoOrario(LocalDateTime ora) {
        //sta a significare che è sempre attivo
        return new OrarioInizioFine(ora, ora);
    }

    @JsonView({View.Dettagli.class, View.DettagliMinimi.class})
    public String getTipo() {
        return "Sempre attivo";
    }

}
