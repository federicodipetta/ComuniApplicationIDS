package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Questa classe implementa il design pattern Singleton per la gestione dei ruoli.
 */
public class GestoreRuoliSingleton {

    private static GestoreRuoliSingleton instance;

    private Map<Utente, List<RuoloComune>> mappaUtenteRuoliComune;

    private GestoreRuoliSingleton() {
        mappaUtenteRuoliComune = new HashMap<>();
    }

    /**
     * Restituisce l'istanza del GestoreRuoliSingleton.
     * @return l'istanza del GestoreRuoliSingleton.
     */
    public static GestoreRuoliSingleton getInstance() {
        if (instance == null) {
            instance = new GestoreRuoliSingleton();
        }
        return instance;
    }

    public List<Comune> getComuniAbilitati(Utente utente, Ruolo ruolo) {
        return mappaUtenteRuoliComune.getOrDefault(utente, new ArrayList<>())
                .stream()
                .parallel()
                .filter(ruoloComune -> ruoloComune.ruolo() == ruolo)
                .map(RuoloComune::comune)
                .toList();
    }

}
