package unicam.cs.ids.punti;

/**
 * Classe per rappresentare le coordinate geografiche di un punto.
 */
public class Coordinate {

    private final double latitudine;

    private final double longitudine;

    public Coordinate(double latitudine, double longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

}
