package unicam.cs.ids.models.tempo;

import java.time.LocalDateTime;

/**
 * Questa interfaccia rappresenta il tempo in cui è attivo un contenuto
 */
public interface Tempo {

    /**
     * Questo metodo controlla se l'orario passato come parametro è contenuto in uno degli orari di inizio e fine
     * @param orario orario da controllare
     * @return true se l'orario passato come parametro è contenuto in uno degli orari di inizio e fine
     */
    boolean attivato(LocalDateTime orario);

    /**
     * Questo metodo restituisce l'orario di inizio e fine successivo a quello passato come parametro
     * @param ora orario da cui partire per trovare il prossimo orario di inizio e fine
     * @return l'orario di inizio e fine successivo a quello passato come parametro
     */
    OrarioInizioFine getProssimoOrario(LocalDateTime ora);

}
