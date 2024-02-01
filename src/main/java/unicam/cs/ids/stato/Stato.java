package unicam.cs.ids.stato;

/**
 * Enumerazione che rappresenta gli stati di un oggetto.
 */
public enum Stato {

    APERTO,
    CHIUSO,
    CONCLUSO,
    ELIMINATO;


    /**
     * Metodo che controlla se lo stato è modificabile.
     * @return true se lo stato è modificabile, false altrimenti.
     */
    public boolean modificabile(){
        return switch (this) {
            case APERTO, CHIUSO -> true;
            case CONCLUSO, ELIMINATO -> false;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case APERTO -> "Aperto";
            case CHIUSO -> "Chiuso";
            case CONCLUSO -> "Concluso";
            case ELIMINATO -> "Eliminato";
        };
    }
}
