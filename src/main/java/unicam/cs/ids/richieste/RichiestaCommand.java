package unicam.cs.ids.richieste;

import org.json.JSONObject;

/**
 * Questa interfaccia rappresenta un comando da eseguire inerente le richieste degli utenti.
 */
public interface RichiestaCommand {

    /**
     * Esegue la richiesta.
     * @param accetta indica se la richiesta deve essere accettata o meno.
     */
    void esegui(boolean accetta);

    /**
     * Restituisce i dettagli minimi della richiesta.
     * @return i dettagli minimi della richiesta.
     */
    JSONObject dettagliMinimi();

    /**
     * Restituisce i dettagli della richiesta.
     * @return i dettagli della richiesta.
     */
    JSONObject dettagli();

}
