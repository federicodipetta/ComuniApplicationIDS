package unicam.cs.ids.richieste;

/**
 * Questa interfaccia rappresenta un comando da eseguire inerente le richieste degli utenti.
 */
public interface RichiestaCommand {

    /**
     * Esegue la richiesta.
     * @param accetta indica se la richiesta deve essere accettata o meno.
     */
    void esegui(boolean accetta);

}
