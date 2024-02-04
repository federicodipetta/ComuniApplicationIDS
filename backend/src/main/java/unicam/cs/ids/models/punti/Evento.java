package unicam.cs.ids.models.punti;

import org.json.JSONObject;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.models.tempo.Tempo;

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

    public Evento(String titolo, String testo, List<File> fileMultimediali, String id, Tempo tempo) {
        super(id,titolo, testo, fileMultimediali, tempo);
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
    public JSONObject dettagli() {
        try {
            JSONObject dettagli = super.dettagli();
            dettagli.put("iscritti", iscritti.size());
            return dettagli;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "Evento{" +
                super.toString() + '\'' +
                "iscritti='" + iscritti.size() + '\'' +
                '}';
    }

}
