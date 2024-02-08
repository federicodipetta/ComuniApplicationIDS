package unicam.cs.ids.models.ruoli;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Notifica {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;


    private String testo;


    public Notifica(String testo) {
        this.testo = testo;
        this.id = id;
    }

    public Notifica() {

    }

    public String getTesto() {
        return testo;
    }

    @Override
    public String toString() {
        return testo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notifica notifica)) return false;
        return id.equals(notifica.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
