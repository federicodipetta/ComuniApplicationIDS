package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

import java.util.HashSet;
import java.util.Set;

public class GestorePiattaforma {

    private GestoreUtenti gestoreUtenti;

    private Set<Comune> comuni;

    public GestorePiattaforma() {
        gestoreUtenti = new GestoreUtenti();
        comuni = new HashSet<>();
    }

    public boolean aggiungiUtente(Utente utente) {
        return gestoreUtenti.aggiungiUtente(utente);
    }

    public boolean aggiungiComune(Comune comune) {
        return comuni.add(comune);
    }

}
