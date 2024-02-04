package unicam.cs.ids.models.ruoli;

import unicam.cs.ids.models.Comune;

/**
 * Classe utilizzata per associare a un comune il ruolo. Serve per rappresentare una coppia.
 */
public record RuoloComune(Comune comune, Ruolo ruolo) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RuoloComune ruoloComune)) return false;
        return comune.equals(ruoloComune.comune) && this.ruolo == ruoloComune.ruolo;
    }

    @Override
    public int hashCode() {
        return comune.hashCode() + ruolo.hashCode();
    }

    @Override
    public String toString() {
        return "RuoloComune{" +
                "comune=" + comune +
                ", ruolo=" + ruolo +
                '}';
    }

}
