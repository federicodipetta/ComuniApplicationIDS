package unicam.cs.ids.models.punti;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Embeddable;
import unicam.cs.ids.view.View;

import java.io.Serializable;

/**
 * Classe per rappresentare le coordinate geografiche di un punto.
 */
@Embeddable
public final class Coordinate implements Serializable {
    @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
    private  Double latitudine;
    @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
    private  Double longitudine;

    /**
     *
     */
    public Coordinate(
            @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
            Double latitudine,
            @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
            Double longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public Coordinate() {

    }

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

    @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
    public Double latitudine() {
        return latitudine;
    }

    @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
    public Double longitudine() {
        return longitudine;
    }


}
