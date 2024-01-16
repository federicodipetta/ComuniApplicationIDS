package unicam.cs.ids.stato;

import unicam.cs.ids.tempo.Tempo;

import java.time.LocalDateTime;

public class SelettoreStato {




    public static Stato nuovoStato(Stato statoAttuale, Tempo tempo, LocalDateTime dataOra){
        if(!statoAttuale.modificiabile()) return statoAttuale;
        return tempo.attivato(dataOra) ?
                Stato.APERTO
                :
                Stato.CHIUSO;
    }
}
