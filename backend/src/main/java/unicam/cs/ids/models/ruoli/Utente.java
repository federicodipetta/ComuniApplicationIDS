package unicam.cs.ids.models.ruoli;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 * Questa classe rappresenta un utente.
 */
@Entity
public final class Utente {
    private  String nomeUtente;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private  String id;

    /**
     * @param nomeUtente il nome dell'utente.
     */
    public Utente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
        this.id = id;
    }

    public Utente() {

    }

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

    public String nomeUtente() {
        return nomeUtente;
    }

    public String id() {
        return id;
    }


}
