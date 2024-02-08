package unicam.cs.ids.models.tempo;

import java.time.LocalDateTime;

/**
 * Observer per il tempo.
 */
public interface ObserverTempo {

    /**
     * Aggiorna il tempo.
     * @param dataOra data e ora correnti.
     */
    void update(LocalDateTime dataOra);
}
