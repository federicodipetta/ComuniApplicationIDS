package unicam.cs.ids.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import unicam.cs.ids.models.punti.PuntoFisico;
import unicam.cs.ids.view.View;

/**
 * Classe utilizzata per rappresentare un comune.
 */
@Entity
public final class Comune {
    @JsonView(View.Dettagli.class)
    private String nome;

    @JsonView(View.Dettagli.class)
    private String provincia;

    @JsonView(View.Dettagli.class)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @JsonView(View.Dettagli.class)
    @OneToOne
    private PuntoFisico puntoComune;

    /**
     *
     */
    public Comune(
            @JsonView(View.Dettagli.class)
            String nome,
            @JsonView(View.Dettagli.class)
            String provincia,
            @JsonView(View.Dettagli.class)
            String id,
            @JsonView(View.Dettagli.class)
            PuntoFisico puntoComune) {
        this.nome = nome;
        this.provincia = provincia;
        this.id = id;
        this.puntoComune = puntoComune;
    }

    public Comune() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comune comune)) return false;
        return id.equals(comune.id());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Comune{" +
                "nome='" + nome + '\'' +
                ", provincia='" + provincia + '\'' +
                ", id='" + id + '\'' +
                ", " + puntoComune.toString() +
                '}';
    }

    public String id() {
        return id;
    }

    @JsonView(View.Dettagli.class)
    public String nome() {
        return nome;
    }

    @JsonView(View.Dettagli.class)
    public String provincia() {
        return provincia;
    }

    @JsonView(View.Dettagli.class)
    public PuntoFisico puntoComune() {
        return puntoComune;
    }


}
