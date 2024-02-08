package unicam.cs.ids.models.richieste;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import unicam.cs.ids.view.View;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class RichiestaAstratta implements RichiestaCommand {

    @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    public String getId() {
        return id;
    }

    private String idc;

    public String getIdc() {
        return idc;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public RichiestaAstratta() { }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RichiestaAstratta richiesta)) return false;
        return richiesta.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
