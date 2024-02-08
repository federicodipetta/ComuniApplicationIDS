package unicam.cs.ids.models.punti;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import unicam.cs.ids.view.View;

import java.util.Set;

/**
 * Classe per rappresentare un punto fisico.
 */
@Entity
public class PuntoFisico {

    @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "latitudine", column = @Column(name = "latitudine")),
            @AttributeOverride(name = "longitudine", column = @Column(name = "longitudine"))
    })
    private  Coordinate coordinate;

    @JsonView(View.Dettagli.class)
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = false)
    private  Set<Contenuto> contenuti;

    private String idc;


    /**
     * Costruttore di un punto fisico.
     * @param coordinate le coordinate del punto fisico
     * @param contenuti i contenuti del punto fisico
     */
    public PuntoFisico(Coordinate coordinate, Set<Contenuto> contenuti) {
        if(coordinate == null || contenuti == null)
            throw new NullPointerException("coordinate o contenuti nulli");
        this.coordinate = coordinate;
        this.contenuti = contenuti;
    }

    public PuntoFisico() { }


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

    public Set<Contenuto> getContenuti() {
        return contenuti;
    }

    @JsonView(View.DettagliMinimi.class)
    public int getNumeroContenuti() {
        return contenuti.size();
    }

    public String getIdc() {
        return idc;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

}
