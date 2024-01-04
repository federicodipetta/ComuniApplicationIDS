package unicam.cs.ids.tempo;

import java.time.LocalDateTime;

/**
 * questa classe rappresenta il tempo in cui è attivo un contenuto
 */
public interface Tempo {
    boolean attivato(LocalDateTime orario);

    OrarioInizioFine getProssimoOrario(LocalDateTime ora);
}
