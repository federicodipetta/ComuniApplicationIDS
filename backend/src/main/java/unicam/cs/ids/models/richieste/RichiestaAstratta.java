package unicam.cs.ids.models.richieste;

public abstract class RichiestaAstratta implements RichiestaCommand {

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
