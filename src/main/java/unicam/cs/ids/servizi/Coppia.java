package unicam.cs.ids.servizi;

/**
 * Rappresenta una coppia di oggetti.
 * @param primo il primo oggetto
 * @param secondo il secondo oggetto
 * @param <A> il tipo del primo oggetto
 * @param <B> il tipo del secondo oggetto
 */
public record Coppia<A, B>(A primo, B secondo) {

    @Override
    public String toString() {
        return "(" + primo + ", " + secondo + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Coppia<?, ?> c)) return false;
        return primo.equals(c.primo) && secondo.equals(c.secondo);
    }

    @Override
    public int hashCode() {
        return primo.hashCode() ^ secondo.hashCode();
    }

}
