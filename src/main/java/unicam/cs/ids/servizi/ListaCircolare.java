package unicam.cs.ids.servizi;

import java.util.ArrayList;

/**
 * Classe utilizzata per implementare una lista circolare.
 * @param <E> Il tipo di elementi contenuti nella lista.
 */
public class ListaCircolare<E> extends ArrayList<E> {

    private int dimensione;

    private int indice;

    public ListaCircolare(int dimensione) {
        super(dimensione);
        this.dimensione = dimensione;
        this.indice = 0;
    }

    public ListaCircolare() {
        this(10);
    }

    @Override
    public boolean add(E e) {
        if(super.size() == dimensione) {
            super.removeFirst();
            super.add(e);
        } else {
            super.add(e);
        }
        return true;
    }

}
