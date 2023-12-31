package unicam.cs.ids.punti;

/**
 * Classe per rappresentare un punto fisico.
 */
public class PuntoFisico {

    private final Coordinate coordinate;

    /**
     * Costruttore del punto fisico.
     * @param coordinate coordinate del punto fisico.co
     */
    public PuntoFisico(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Restituisce la coordinata del punto fisico.
     * @return
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }
}
