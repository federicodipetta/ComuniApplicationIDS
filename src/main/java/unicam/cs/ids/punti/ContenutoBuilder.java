package unicam.cs.ids.punti;

import unicam.cs.ids.ruoli.Utente;
import unicam.cs.ids.stato.Stato;
import unicam.cs.ids.tempo.SempreAttivo;
import unicam.cs.ids.tempo.Tempo;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ContenutoBuilder {

    private boolean tempoSettato;
    private String titolo;

    private String testo;

    private List<File> fileMultimediali;

    private int id;

    private Tempo tempo;

    private Stato stato;

    private List<Contenuto> contenuti;

    private List<Utente> iscritti;

    /**
     * inzializza il builder con i valori di default.
     */
    public ContenutoBuilder() {
        this.id = -1;
        this.fileMultimediali = new LinkedList<>();
        this.contenuti = new LinkedList<>();
        this.tempo = new SempreAttivo();
        this.iscritti = new LinkedList<>();
        this.stato = null;
    }

     private boolean checkString(String stringa){
        return stringa == null && stringa.equals("");
    }

    /**
     * aggiunge il titolo tra i parametri del conenuto da costruire.
     * @param titolo il titolo del contenuto
     * @return questo builder
     * @throws IllegalArgumentException se il titolo è nullo
     */
    public ContenutoBuilder setTitolo(String titolo){
        if (checkString(titolo))
            throw new IllegalArgumentException("Titolo non valido");
        this.titolo = titolo;
        return this;
    }

    /**
     * aggiunge il testo tra i parametri del conenuto da costruire.
     * @param testo il testo del contenuto
     * @return questo builder
     * @throws IllegalArgumentException se il testo è nullo
     */
    public ContenutoBuilder setTesto(String testo){
        if (checkString(testo))
            throw new IllegalArgumentException("Testo non valido");
        this.testo = testo;
        return this;
    }

    /**
     * aggiunge un file tra i parametri del conenuto da costruire.
     * @param fileMultimediale il file da aggiungere
     * @return  questo builder
     * @throws IllegalArgumentException se il file è nullo
     */
    public ContenutoBuilder setFile(File fileMultimediale){
        if(addElementToList(this.fileMultimediali, fileMultimediale))
            throw new IllegalArgumentException("File non valido");
        return this;
    }

    /**
     * aggiunge una lista di file tra i parametri del conenuto da costruire.
     * @param fileMultimediali i file da aggiungere
     * @return questo builder
     * @throws IllegalArgumentException se la lista è nulla
     */
    public ContenutoBuilder setFiles(Collection<File> fileMultimediali) {
        if(addElementToList(this.fileMultimediali, fileMultimediali))
            throw new IllegalArgumentException("File non valido");
        return this;
    }

    /**
     * aggiunge un id tra i parametri del conenuto da costruire.
     * @param id l'id del contenuto
     * @return questo builder
     * @throws IllegalArgumentException se l'id è negativo
     */
    public ContenutoBuilder setId(int id){
        if (id < 0)
            throw new IllegalArgumentException("Id non valido");
        this.id = id;
        return this;
    }

    /**
     * aggiunge un tempo tra i parametri del conenuto da costruire.
     * @param tempo il tempo del contenuto
     * @return questo builder
     * @throws IllegalArgumentException se il tempo è nullo
     */
    public ContenutoBuilder setTempo(Tempo tempo){
        if (tempo == null)
            throw new IllegalArgumentException("Tempo non valido");
        this.tempo = tempo;
        if(tempo instanceof SempreAttivo){
            tempoSettato = false;
        }else{
            tempoSettato = true;
        }
        return this;
    }

    /**
     * aggiunge uno stato tra i parametri del conenuto da costruire.
     * @param stato lo stato del contenuto
     * @return questo builder
     */
    public ContenutoBuilder setStato(Stato stato){
        if (stato == null) throw new IllegalArgumentException("Stato non valido");
        this.stato = stato;
        return this;
    }

    /**
     * aggiunge una lista di contenuti tra i parametri del conenuto da costruire.
     * @param contenuti i contenuti da aggiungere
     * @return questo builder
     */
    public ContenutoBuilder setContenuti(List<Contenuto> contenuti) {
        if(!addElementToList(this.contenuti, contenuti))
            throw new IllegalArgumentException("Contenuti non validi");
        return this;
    }

    /**
     * aggiunge un contenuto tra i parametri del conenuto da costruire.
     * @param contenuto il contenuto da aggiungere
     * @return questo builder
     */
    public ContenutoBuilder setContenuto(Contenuto contenuto) {
        if (!addElementToList(this.contenuti, contenuto))
            throw new IllegalArgumentException("Contenuto non valido");
        return this;
    }

    /**
     * aggiunge una lista di iscritti tra i parametri del conenuto da costruire.
     * @param iscritti gli iscritti da aggiungere
     * @return questo builder
     */
    public ContenutoBuilder setIscritti(List<Utente> iscritti) {
        if(!addElementToList(this.iscritti, iscritti))
            throw new IllegalArgumentException("Iscritti non validi");
        return this;
    }

    /**
     * aggiunge un iscritto tra i parametri del conenuto da costruire.
     * @param iscritto l'iscritto da aggiungere
     * @return questo builder
     */
    public ContenutoBuilder addIscritto(Utente iscritto) {
        if (!addElementToList(this.iscritti, iscritto))
            throw new IllegalArgumentException("Iscritto non valido");
        return this;

    }

    /**
     * costruisce il contenuto con i parametri settati.
     * @return il contenuto costruito
     * @throws IllegalStateException se non sono stati settati tutti i parametri minimi
     */
    public Contenuto build() {
        if (titolo == null || testo == null || id < 0 || stato == null) {
            throw new IllegalStateException("Non tutti i parametri minimi sono stati settati");
        }
        Contenuto c ;
        //solo l'itinierario ha i contenuti
        if(!this.contenuti.isEmpty())
            c = new Itinerario(titolo, testo, fileMultimediali,contenuti, id, tempo);
        //solo l'evento ha iscritti o tempo non sempre attivo
        else if(tempoSettato) {
            Evento e = new Evento(titolo, testo, fileMultimediali, id, tempo);
            e.iscriviAll(iscritti);
            c = e;
        }else
            c = new PuntoInteresse(titolo, testo, fileMultimediali, id);
        if(stato != null)
            c.setStato(stato);
        return c;
    }

    /**
     * resetta i parametri del contenuto da costruire.
     * @return questo builder
     */
    public ContenutoBuilder reset() {
        this.titolo = null;
        this.testo = null;
        this.fileMultimediali = new LinkedList<>();
        this.id = -1;
        this.tempo = new SempreAttivo();
        this.stato = null;
        this.contenuti = new LinkedList<>();
        this.iscritti = new LinkedList<>();
        return this;
    }



    private <T> boolean addElementToList(Collection<T> collection, T element){
        if (element == null) {
            return false;
        }
        return collection.add(element);
    }

    private <T> boolean addElementToList(Collection<T> collection, Collection<T> elements){
        if (elements == null) {
            return false;
        }
        return collection.addAll(elements);
    }
}
