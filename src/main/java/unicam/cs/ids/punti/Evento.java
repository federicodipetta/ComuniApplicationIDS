package unicam.cs.ids.punti;

import unicam.cs.ids.ruoli.Utente;
import unicam.cs.ids.tempo.Tempo;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe per rappresentare un evento.
 */
public class Evento extends Contenuto {

    private final Set<Utente> iscritti;

    public Evento(String titolo, String testo, List<File> fileMultimediali, int id, Tempo tempo) {
        super(titolo, testo, fileMultimediali, id, tempo);
        this.iscritti = new HashSet<>();
    }

    /**
     * Metodo per iscrivere un utente all'evento.
     * @param utente l'utente da iscrivere all'evento.
     * @return true se l'utente è stato iscritto all'evento, false altrimenti.
     */
    public boolean iscrizione(Utente utente) {
        return iscritti.add(utente);
    }

    /**
     * Metodo per disiscrivere un utente dall'evento.
     * @param utente l'utente da disiscrivere dall'evento.
     * @return true se l'utente è stato disiscritto dall'evento, false altrimenti.
     */
    public boolean disiscrizione(Utente utente) {
        return iscritti.remove(utente);
    }

    public boolean iscriviAll(Collection<Utente> utente) {
        return iscritti.addAll(utente);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento evento)) return false;
        return super.equals(evento) && iscritti.equals(evento.iscritti);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + iscritti.hashCode();
    }

    @Override
    public String toString() {
        return "Evento{" +
                super.toString() + '\'' +
                "iscritti='" + iscritti.size() + '\'' +
                '}';
    }

}
