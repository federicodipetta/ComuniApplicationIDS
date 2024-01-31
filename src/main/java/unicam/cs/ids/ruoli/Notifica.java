package unicam.cs.ids.ruoli;

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
        if(o instanceof Notifica) {
            Notifica notifica = (Notifica) o;
            return notifica.getTesto().equals(testo);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return testo.hashCode();
    }

}
