package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Questa classe implementa il design pattern Singleton per la gestione dei ruoli.
 */
public class GestoreRuoliSingleton {

    private static GestoreRuoliSingleton instance;

    private Map<Utente, Set<RuoloComune>> mappaUtenteRuoliComune;

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

    public Set<Comune> getComuniAbilitati(Utente utente, Ruolo ruolo) {
        return mappaUtenteRuoliComune.getOrDefault(utente, new HashSet<>())
                .stream()
                .parallel()
                .filter(ruoloComune -> ruoloComune != null && ruoloComune.ruolo() == ruolo)
                .map(RuoloComune::comune)
                .collect(Collectors.toSet());
    }

    /**
     * Associa a un utente un ruolo in un comune.
     * @param utente l'utente a cui si vuole associare il ruolo.
     * @param ruoloComune La coppia ruolo-comune.
     * @return true se l'utente è stato associato al ruolo nel comune, false altrimenti.
     */
    public boolean aggiungiUtenteRuoloComune(Utente utente, RuoloComune ruoloComune) {
        if(!mappaUtenteRuoliComune.containsKey(utente)) {
            Set<RuoloComune> insieme = new HashSet<>();
            insieme.add(ruoloComune);
            mappaUtenteRuoliComune.put(utente, insieme);
            return true;
        } else { // L'utente è già presente nella mappa
            return mappaUtenteRuoliComune.get(utente).add(ruoloComune);
        }
    }



}
