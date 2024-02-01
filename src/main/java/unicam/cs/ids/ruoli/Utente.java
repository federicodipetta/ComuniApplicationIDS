package unicam.cs.ids.ruoli;

/**
 * Questa classe rappresenta un utente.
 * @param nomeUtente il nome dell'utente.
 * @param id l'id dell'utente.
 */
public record Utente(String nomeUtente, String id) {

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Utente utente)) return false;
        return utente.id().equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return nomeUtente + " (" + id + ")";
    }

}
