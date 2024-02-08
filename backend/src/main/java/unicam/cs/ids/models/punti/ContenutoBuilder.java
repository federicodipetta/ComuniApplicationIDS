package unicam.cs.ids.models.punti;

import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.models.stato.SelettoreStato;
import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.SempreAttivo;
import unicam.cs.ids.models.tempo.TempoAstratto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ContenutoBuilder {

    private boolean tempoSettato;

    private String titolo;

    private String testo;

    private List<MultipartFile> fileMultimediali;

    private String id;

    private TempoAstratto tempo;

    private Stato stato;

    private List<Contenuto> contenuti;

    private List<Utente> iscritti;

    private boolean ordinato;


    /**
     * Inizializza il builder con i valori di default.
     */
    public ContenutoBuilder() {
        this.id = "";
        this.fileMultimediali = new LinkedList<>();
        this.contenuti = new LinkedList<>();
        this.tempo = new SempreAttivo();
        this.iscritti = new LinkedList<>();
        this.stato = null;
        this.ordinato = false;
    }


     private boolean checkString(String stringa){
        return stringa == null || stringa.isEmpty() || stringa.isBlank() ;
    }

    /**
     * Aggiunge il titolo tra i parametri del contenuto da costruire.
     * @param titolo il titolo del contenuto.
     * @return questo builder.
     * @throws IllegalArgumentException se il titolo è nullo.
     */
    public ContenutoBuilder setTitolo(String titolo){
        if (checkString(titolo))
            throw new IllegalArgumentException("Titolo non valido");
        this.titolo = titolo;
        return this;
    }

    /**
     * Aggiunge il testo tra i parametri del contenuto da costruire.
     * @param testo il testo del contenuto.
     * @return questo builder.
     * @throws IllegalArgumentException se il testo è nullo.
     */
    public ContenutoBuilder setTesto(String testo){
        if (checkString(testo))
            throw new IllegalArgumentException("Testo non valido");
        this.testo = testo;
        return this;
    }

    /**
     * Aggiunge un file tra i parametri del contenuto da costruire.
     * @param fileMultimediale il file da aggiungere.
     * @return  questo builder.
     * @throws IllegalArgumentException se il file è nullo.
     */
    public ContenutoBuilder setFile(MultipartFile fileMultimediale){
        if(addElementToList(this.fileMultimediali, fileMultimediale))
            throw new IllegalArgumentException("File non valido");
        return this;
    }

    /**
     * Aggiunge una lista di file tra i parametri del contenuto da costruire.
     * @param fileMultimediali i file da aggiungere.
     * @return questo builder.
     * @throws IllegalArgumentException se la lista è nulla.
     */
    public ContenutoBuilder setFiles(Collection<MultipartFile> fileMultimediali) {
        if(addElementToList(this.fileMultimediali, fileMultimediali))
            throw new IllegalArgumentException("File non valido");
        return this;
    }

    /**
     * Aggiunge un id tra i parametri del contenuto da costruire.
     * @param id l'id del contenuto.
     * @return questo builder.
     * @throws IllegalArgumentException se l'id è negativo.
     */
    public ContenutoBuilder setId(String id){
        this.id = id;
        return this;
    }

    /**
     * Aggiunge un tempo tra i parametri del contenuto da costruire.
     * @param tempo il tempo del contenuto.
     * @return questo builder.
     * @throws IllegalArgumentException se il tempo è nullo.
     */
    public ContenutoBuilder setTempo(TempoAstratto tempo){
        if (tempo == null)
            throw new IllegalArgumentException("Tempo non valido");
        this.tempo = tempo;
        tempoSettato = !(tempo instanceof SempreAttivo);
        return this;
    }

    /**
     * Aggiunge uno stato tra i parametri del contenuto da costruire.
     * @param stato lo stato del contenuto.
     * @return questo builder.
     */
    public ContenutoBuilder setStato(Stato stato){
        if (stato == null) throw new IllegalArgumentException("Stato non valido");
        this.stato = stato;
        return this;
    }

    /**
     * Aggiunge una lista di contenuti tra i parametri del contenuto da costruire.
     * @param contenuti i contenuti da aggiungere.
     * @return questo builder.
     */
    public ContenutoBuilder setContenuti(List<Contenuto> contenuti, boolean ordianto) {
        this.ordinato = ordianto;
        if(!addElementToList(this.contenuti, contenuti))
            throw new IllegalArgumentException("Contenuti non validi");
        return this;
    }

    /**
     * Aggiunge un contenuto tra i parametri del contenuto da costruire.
     * @param contenuto il contenuto da aggiungere.
     * @return questo builder.
     */
    public ContenutoBuilder setContenuto(Contenuto contenuto, boolean ordianto) {
        this.ordinato = ordianto;
        if (!addElementToList(this.contenuti, contenuto))
            throw new IllegalArgumentException("Contenuto non valido");
        return this;
    }

    /**
     * Aggiunge una lista di iscritti tra i parametri del contenuto da costruire.
     * @param iscritti gli iscritti da aggiungere.
     * @return questo builder.
     */
    public ContenutoBuilder setIscritti(List<Utente> iscritti) {
        if(!addElementToList(this.iscritti, iscritti))
            throw new IllegalArgumentException("Iscritti non validi");
        return this;
    }

    /**
     * Aggiunge un iscritto tra i parametri del contenuto da costruire.
     * @param iscritto l'iscritto da aggiungere.
     * @return questo builder.
     */
    public ContenutoBuilder addIscritto(Utente iscritto) {
        if (!addElementToList(this.iscritti, iscritto))
            throw new IllegalArgumentException("Iscritto non valido");
        return this;

    }

    /**
     * Costruisce il contenuto con i parametri settati.
     * @return il contenuto costruito.
     * @throws IllegalStateException se non sono stati settati tutti i parametri minimi.
     */
    public Contenuto build() {
        if (checkString(titolo) || checkString(testo)) {
            throw new IllegalStateException("Non tutti i parametri minimi sono stati settati");
        }
        stato = stato== null ? SelettoreStato.nuovoStato(Stato.APERTO,this.tempo, LocalDateTime.now()) : stato;
        Contenuto c ;
        //solo l'itinerario ha i contenuti
        if(!this.contenuti.isEmpty())
            c = new Itinerario(titolo, testo, fileMultimediali,contenuti,tempo);
        //solo l'evento ha iscritti o tempo non sempre attivo
        else if(tempoSettato) {
            Evento e = new Evento(titolo, testo, fileMultimediali, tempo);
            e.iscriviAll(iscritti);
            c = e;
        }else
            c = new PuntoInteresse(titolo, testo, fileMultimediali);
        if(stato != null)
            c.setStato(stato);
        return c;
    }

    /**
     * Resetta i parametri del contenuto da costruire.
     * @return questo builder.
     */
    public ContenutoBuilder reset() {
        this.titolo = null;
        this.testo = null;
        this.fileMultimediali = new LinkedList<>();
        this.id = "";
        this.tempo = new SempreAttivo();
        this.stato = null;
        this.contenuti = new LinkedList<>();
        this.iscritti = new LinkedList<>();
        this.ordinato = false;
        return this;
    }



    private <T> boolean addElementToList(Collection<T> collection, T element){
        if (element !=null)
            return collection.add(element);
        return true;
    }

    private <T> boolean addElementToList(Collection<T> collection, Collection<T> elements){
        if(elements != null)
            return collection.addAll(elements);
        return true;
    }
}
