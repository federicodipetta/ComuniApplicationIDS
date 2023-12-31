package unicam.cs.ids.punti;

public class PuntoFisico {

    private final Coordinate coordinate;

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
