package unicam.cs.ids.models;
import com.fasterxml.jackson.annotation.JsonView;
import unicam.cs.ids.models.punti.PuntoFisico;
import unicam.cs.ids.view.View;

/**
 * Classe utilizzata per rappresentare un comune.
 */
public record Comune(
        @JsonView(View.Dettagli.class)
        String nome,
        @JsonView(View.Dettagli.class)
        String provincia,
        @JsonView(View.Dettagli.class)
        String id,
        @JsonView(View.Dettagli.class)
        PuntoFisico puntoComune) {

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


}
