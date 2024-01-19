package unicam.cs.ids.tempo;

import java.time.LocalDateTime;

/**
 * Questa classe rappresenta un orario di inizio e fine, quindi un intervallo di tempo.
 * Se un orario inizia e finisce nello stesso momento significa che il periodo di tempo è sempre attivo.
 * @param inizio l'orario di inizio
 * @param fine l'orario di fine
 */
public record OrarioInizioFine(LocalDateTime inizio,LocalDateTime fine) implements Comparable<OrarioInizioFine> {

    /**
     * Controlla se una parte dell'orario è compresa tra l'orario di inizio e l'orario di fine
     * @param orarioInizioFine l'orario da controllare
     * @return true se l'orario è compreso tra l'orario di inizio e l'orario di fine
     */
    public boolean sovrapposto(OrarioInizioFine orarioInizioFine){
        return (inizio.isBefore(orarioInizioFine.fine) ||inizio.isEqual(orarioInizioFine.fine))
                &&
                (fine.isAfter(orarioInizioFine.inizio)||fine.isEqual(orarioInizioFine.inizio));
    }

    /**
     * Controlla se un orario è compreso tra l'orario di inizio e l'orario di fine
     * @param orario l'orario da controllare
     * @return true se l'orario è compreso tra l'orario di inizio e l'orario di fine compresi gli estremi
     *         false altrimenti
     */
    public boolean contiene(LocalDateTime orario) {
        return (inizio.isBefore(orario) || inizio.isEqual(orario)) && (fine.isAfter(orario) || fine.isEqual(orario));
    }

    /**
     * Confronta due orari di inizio e fine.
     * Un orario è minore di un altro se il suo orario di fine è minore dell'orario di fine dell'altro
     * @param o l'orario da confrontare
     * @return un valore negativo, zero o positivo se l'orario è minore, uguale o maggiore
     */
    @Override
    public int compareTo(OrarioInizioFine o) {
        return fine.compareTo(o.fine);
    }

    @Override
    public String toString() {
        if(inizio.equals(fine)) return "Sempre attivo";
    	return "Inizio: " + inizio + " Fine: " + fine;
    }
}
