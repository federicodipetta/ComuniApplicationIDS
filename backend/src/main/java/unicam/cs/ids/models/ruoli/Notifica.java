package unicam.cs.ids.models.ruoli;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Id;

@Entity
public class Notifica {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String testo;


    public Notifica(String testo) {
        this.testo = testo;
    }

    public Notifica() { }


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


    public String getTesto() {
        return testo;
    }

}
