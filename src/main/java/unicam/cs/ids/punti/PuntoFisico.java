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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuntoFisico)) return false;
        PuntoFisico that = (PuntoFisico) o;
        return coordinate.equals(that.coordinate);
    }

}
