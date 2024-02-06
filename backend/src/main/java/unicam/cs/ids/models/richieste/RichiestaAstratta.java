package unicam.cs.ids.models.richieste;

import com.fasterxml.jackson.annotation.JsonView;
import unicam.cs.ids.view.View;

public abstract class RichiestaAstratta implements RichiestaCommand {
    @JsonView({View.DettagliMinimi.class, View.Dettagli.class})
    private final String id;

    public RichiestaAstratta(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

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
