package unicam.cs.ids.ruoli;

public class Utente {

    private final String nomeUtente;

    private final String id;

    public Utente(String nomeUtente, String id) {
        this.nomeUtente = nomeUtente;
        this.id = id;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getId() {
        return id;
    }

}
