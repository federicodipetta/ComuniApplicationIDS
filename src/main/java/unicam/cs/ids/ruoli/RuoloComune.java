package unicam.cs.ids.ruoli;

import unicam.cs.ids.Comune;

/**
 * Classe utilizzata per associare a un comune il ruolo. Serve per rappresentare una coppia.
 */
public record RuoloComune(Comune comune, Ruolo ruolo) {
}
