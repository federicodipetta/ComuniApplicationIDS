package unicam.cs.ids.models.punti;

import com.fasterxml.jackson.annotation.JsonView;
import unicam.cs.ids.view.View;

/**
 * Classe per rappresentare le coordinate geografiche di un punto.
 */
public record Coordinate(
        @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
        Double latitudine,
        @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
        Double longitudine) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate coordinate)) return false;
        Integer latitudineInt = (int) (latitudine * 10000);
        Integer longitudineInt = (int) (longitudine * 10000);
        Integer altraLatitudineInt = (int) (coordinate.latitudine * 10000);
        Integer altraLongitudineInt = (int) (coordinate.longitudine * 10000);
        return latitudineInt.equals(altraLatitudineInt) && longitudineInt.equals(altraLongitudineInt);
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
