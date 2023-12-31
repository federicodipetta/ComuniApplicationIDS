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


    /**
     * Restituisce il nome del comune.
     * @return nome del comune.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce la provincia del comune.
     * @return provincia del comune.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Restituisce l'id del comune.
     * @return id del comune.
     */
    public String getId() {
        return id;
    }

    /**
     * Restituisce il punto fisico del comune.
     * @return punto fisico del comune.
     */
    public PuntoFisico getPuntoComune() {
        return puntoComune;
    }
}
