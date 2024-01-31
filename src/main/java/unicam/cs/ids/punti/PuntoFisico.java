package unicam.cs.ids.punti;

import java.util.List;

/**
 * Classe per rappresentare un punto fisico.
 */
public class PuntoFisico {

    private final Coordinate coordinate;

    private final List<Contenuto> contenuti;

    /**
     * Costruttore di un punto fisico.
     * @param coordinate le coordinate del punto fisico
     * @param contenuti i contenuti del punto fisico
     */
    public PuntoFisico(Coordinate coordinate, List<Contenuto> contenuti) {
        if(coordinate == null || contenuti == null)
            throw new NullPointerException("coordinate o contenuti nulli");
        this.coordinate = coordinate;
        this.contenuti = contenuti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuntoFisico puntoFisico)) return false;
        return coordinate.equals(puntoFisico.coordinate);
    }

    @Override
    public int hashCode() {
        return coordinate.hashCode();
    }

    @Override
    public String toString() {
        return "PuntoFisico{" +
                coordinate +
                ", contenuti=" + contenuti.size() +
                '}';
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public List<Contenuto> getContenuti() {
        return contenuti;
    }

}
