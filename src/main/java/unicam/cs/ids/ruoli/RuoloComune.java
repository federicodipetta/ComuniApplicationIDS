package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

/**
 * Classe utilizzata per associare a un comune il ruolo. Serve per rappresentare una coppia.
 */
public record RuoloComune(Comune comune, Ruolo ruolo) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuoloComune)) return false;
        RuoloComune that = (RuoloComune) o;
        return comune.equals(that.comune) && ruolo == that.ruolo;
    }
}
