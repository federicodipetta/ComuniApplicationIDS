package unicam.cs.ids.models.ruoli;

public class Notifica {

    private final String testo;
    private final String id;

    public Notifica(String testo, String id) {
        this.testo = testo;
        this.id = id;
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
