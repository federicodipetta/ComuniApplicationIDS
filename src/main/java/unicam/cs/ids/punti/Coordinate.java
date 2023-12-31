package unicam.cs.ids.punti;

/**
 * Classe per rappresentare le coordinate geografiche di un punto.
 */
public class Coordinate {

    private final Double latitudine;

    private final Double longitudine;

    public Coordinate(Double latitudine, Double longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    /**
     * Restituisce la latitudine del punto.
     * @return latitudine del punto.
     */
    public Double getLatitudine() {
        return latitudine;
    }

    /**
     * Restituisce la longitudine del punto.
     * @return longitudine del punto.
     */
    public Double getLongitudine() {
        return longitudine;
    }

}
