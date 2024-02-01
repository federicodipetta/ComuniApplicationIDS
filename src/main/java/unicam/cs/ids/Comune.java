package unicam.cs.ids;
import unicam.cs.ids.punti.PuntoFisico;

/**
 * Classe utilizzata per rappresentare un comune.
 */
public record Comune(String nome, String provincia, String id, PuntoFisico puntoComune) {

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
