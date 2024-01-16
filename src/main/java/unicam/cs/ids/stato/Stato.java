package unicam.cs.ids.stato;

public enum Stato {
    APERTO,
    CHIUSO,
    CONCLUSO,
    ELIMINATO;





    public boolean modificiabile(){
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
