package unicam.cs.ids.richieste;

import unicam.cs.ids.ruoli.Utente;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Questa classe rappresenta un gestore di richieste.
 */
public class GestoreRichieste {
    private Map<Utente,Set<RichiestaCommand>> mappaRichiesteContest;
    private Set<RichiestaCommand> richieste;
    public GestoreRichieste() {
        this.richieste = new HashSet<>();
        this.mappaRichiesteContest = new HashMap<>();
    }

    /**
     * Questo metodo permette di ottenere le richieste legate all'animatore.
     * @param utente l'animatore di cui si vogliono ottenere le richieste.
     * @return le richieste legate all'animatore.
     */
    public Set<RichiestaCommand> getRichiesteContest(Utente utente) {
        if(mappaRichiesteContest.containsKey(utente)) {
            return new HashSet<>(mappaRichiesteContest.get(utente));
        } else {
            return new HashSet<>();
        }
    }

    public Set<RichiestaCommand> getRichieste() {
        return new HashSet<>(richieste);
    }


    public void aggiungiRichiesta(RichiestaCommand richiesta) {
        richieste.add(richiesta);
    }

    public void valutaRichiesta(RichiestaCommand richiesta, boolean accetta) {
        if(this.richieste.contains(richiesta) ){
            richiesta.esegui(accetta);
            richieste.remove(richiesta);
        }
    }

    public void aggiungiRichiestaContest(Utente utente, RichiestaCommand richiesta) {
        if(mappaRichiesteContest.containsKey(utente)) {
            mappaRichiesteContest.get(utente).add(richiesta);
        } else {
            Set<RichiestaCommand> richieste = new HashSet<>();
            richieste.add(richiesta);
            mappaRichiesteContest.put(utente,richieste);
        }
    }

    public void valutaRichiestaContest(Utente utente,RichiestaCommand richiesta, boolean accetta) {
        if(mappaRichiesteContest.containsKey(utente) && mappaRichiesteContest.get(utente).contains(richiesta)) {
            richiesta.esegui(accetta);
            mappaRichiesteContest.remove(utente);
        }
    }




}
