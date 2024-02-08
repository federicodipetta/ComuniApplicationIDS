package unicam.cs.ids.models.stato;

import unicam.cs.ids.models.tempo.Tempo;

import java.time.LocalDateTime;

/**
 * Classe che si occupa di selezionare lo stato di un oggetto in base al tempo.
 */
public class SelettoreStato {

    /**
     * Metodo che seleziona lo stato di un oggetto in base al tempo.
     * @param statoAttuale Stato attuale dell'oggetto.
     * @param tempo Tempo in cui l'oggetto è attivo.
     * @param dataOra Data e ora attuali.
     * @return Stato aggiornato (o non) dell'oggetto.
     */
    public static Stato nuovoStato(Stato statoAttuale, Tempo tempo, LocalDateTime dataOra){
        if(!statoAttuale.modificabile()) return statoAttuale;
        return tempo.attivato(dataOra) ?
                Stato.APERTO
                :
                Stato.CHIUSO;
    }
}
