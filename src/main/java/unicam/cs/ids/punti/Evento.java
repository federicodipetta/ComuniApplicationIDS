package unicam.cs.ids.punti;

import unicam.cs.ids.ruoli.Utente;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per rappresentare un evento.
 */
public class Evento extends Contenuto {

    private final List<Utente> iscritti;

    public Evento(String titolo, String testo, List<File> fileMultimediali, int id) {
        super(titolo, testo, fileMultimediali, id);
        this.iscritti = new ArrayList<>();
    }

    /**
     * Metodo per ottenere la lista degli iscritti all'evento.
     * @return la lista degli iscritti all'evento.
     */
    public List<Utente> getIscritti() {
        return iscritti;
    }

    /**
     * Metodo per iscrivere un utente all'evento.
     * @param utente l'utente da iscrivere all'evento.
     * @return true se l'utente è stato iscritto all'evento, false altrimenti.
     */
    public boolean iscrizione(Utente utente) {
        if (this.iscritti.contains(utente)) {
            return false;
        }
        this.iscritti.add(utente);
        return true;
    }

    /**
     * Metodo per disiscrivere un utente dall'evento.
     * @param utente l'utente da disiscrivere dall'evento.
     * @return true se l'utente è stato disiscritto dall'evento, false altrimenti.
     */
    public boolean disiscrizione(Utente utente) {
        if (this.iscritti.contains(utente)) {
            this.iscritti.remove(utente);
            return true;
        }
        return false;
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
                "contenuto='" + super.toString() + '\'' +
                "iscritti='" + iscritti.size() + '\'' +
                '}';
    }

}
