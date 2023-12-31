package unicam.cs.ids;
import unicam.cs.ids.punti.PuntoFisico;

/**
 * Classe utilizzata per rappresentare un comune.
 */
public class Comune {

    private final String nome;

    private final String provincia;

    private final String id;

    private final PuntoFisico puntoComune;

    public Comune(String nome, String provincia, String id, PuntoFisico puntoComune) {
        this.nome = nome;
        this.provincia = provincia;
        this.id = id;
        this.puntoComune = puntoComune;
    }



}
