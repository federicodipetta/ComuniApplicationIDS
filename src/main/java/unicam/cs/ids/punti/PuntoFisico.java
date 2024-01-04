package unicam.cs.ids.punti;

import java.util.List;

/**
 * Classe per rappresentare un punto fisico.
 */
public record PuntoFisico(Coordinate coordinate, List<Contenuto> contenuti) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuntoFisico puntoFisico)) return false;
        return coordinate.equals(puntoFisico.coordinate) && contenuti.equals(puntoFisico.contenuti);
    }

    @Override
    public int hashCode() {
        return coordinate.hashCode() + contenuti.hashCode();
    }

    @Override
    public String toString() {
        return "PuntoFisico{" +
                coordinate.toString() +
                ", contenuti=" + contenuti.size() +
                '}';
    }

}
