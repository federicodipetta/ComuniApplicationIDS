package unicam.cs.ids.tempo;

import java.time.LocalDateTime;

/**
 * Questa classe rappresenta un orario di inizio e fine, quindi un intervallo di tempo.
 *
 * Se un orario inizia e finisce nello stesso momento significa che è sempre attivo il periodo di tempo
 * @param inizio l'orario di inizio
 * @param fine l'orario di fine
 */
public record OrarioInizioFine(LocalDateTime inizio,LocalDateTime fine) implements Comparable<OrarioInizioFine> {
    /**
     * controlla se una parte dell'orario è compreso tra l'orario di inizio e l'orario di fine
     * @param orarioInizioFine l'orario da controllare
     * @return true se l'orario è compreso tra l'orario di inizio e l'orario di fine
     */
    public boolean sovrapposto(OrarioInizioFine orarioInizioFine){
        return (inizio.isBefore(orarioInizioFine.fine) ||inizio.isEqual(orarioInizioFine.fine))
                &&
                (fine.isAfter(orarioInizioFine.inizio)||fine.isEqual(orarioInizioFine.inizio));
    }

    /**
     * controlla se un orario è compreso tra l'orario di inizio e l'orario di fine
     * @param orario l'orario da controllare
     * @return true se l'orario è compreso tra l'orario di inizio e l'orario di fine compresi gli estremi
     *         false altrimenti
     */
    public boolean contiene(LocalDateTime orario){
        return inizio.isBefore(orario) && fine.isAfter(orario);
    }

    /**
     * Confronta due orari di inizio e fine.
     * un orario è minore di un altro se il suo orario di fine è minore dell'orario di fine dell'altro
     * @param o l'orario da conforntare
     * @return un valore negativo, zero o positivo se l'orario è minore, uguale o maggiore
     */
    @Override
    public int compareTo(OrarioInizioFine o) {
        return fine.compareTo(o.fine);
    }


    public String toString() {
        if(inizio.equals(fine)) return "Sempre attivo";
    	return "Inizio: " + inizio + " Fine: " + fine;
    }
}
