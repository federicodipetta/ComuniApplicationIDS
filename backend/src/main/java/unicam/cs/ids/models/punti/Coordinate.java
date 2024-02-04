package unicam.cs.ids.models.punti;

import com.fasterxml.jackson.annotation.JsonView;
import unicam.cs.ids.view.View;

/**
 * Classe per rappresentare le coordinate geografiche di un punto.
 */
public record Coordinate(
        @JsonView(View.Dettagli.class)
        Double latitudine,
        @JsonView(View.Dettagli.class)
        Double longitudine) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate coordinate)) return false;
        return latitudine.equals(coordinate.latitudine) && longitudine.equals(coordinate.longitudine);
    }

    @Override
    public int hashCode() {
        return latitudine.hashCode() + longitudine.hashCode();
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                '}';
    }

}
