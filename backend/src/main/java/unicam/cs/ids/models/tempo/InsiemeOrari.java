package unicam.cs.ids.models.tempo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Questa classe rappresenta un insieme di orari di inizio e fine,
 * gli orari sono rappresentati da un oggetto {@link OrarioInizioFine}
 * che contiene un orario di inizio e uno di fine.
 */
public class InsiemeOrari implements Tempo{

    private final List<OrarioInizioFine> orarioInizioFineList;

    /**
     * Costruttore di un insieme di orari
     * @param orarioInizioFineList collezione di orari di inizio e fine
     */
    public InsiemeOrari (Collection<OrarioInizioFine> orarioInizioFineList){
        this.orarioInizioFineList = new ArrayList<>(orarioInizioFineList);
        this.orarioInizioFineList.removeIf(x -> x.inizio().isAfter(x.fine()));
        this.orarioInizioFineList.sort(OrarioInizioFine::compareTo);
        for(int i = 0; i< this.orarioInizioFineList.size()-1; i++){
            if(this.orarioInizioFineList.get(i).sovrapposto(this.orarioInizioFineList.get(i+1))){
                this.orarioInizioFineList.set(i,
                        new OrarioInizioFine(
                                this.orarioInizioFineList.get(i).inizio().isBefore(this.orarioInizioFineList.get(i+1).inizio())?
                                this.orarioInizioFineList.get(i).inizio()
                                        : //controllo quale orario inizia prima e prendo quello
                                this.orarioInizioFineList.get(i+1).inizio(),
                               // quello che finisce dopo è sempre "i+1" perché è ordinato
                                this.orarioInizioFineList.get(i+1).fine()));
                this.orarioInizioFineList.remove(i+1);
                i--;
            }
        }
    }


    @Override
    public boolean attivato(LocalDateTime orario) {
        return binarysearch(orario) != null;
    }

    @Override
    public OrarioInizioFine getProssimoOrario(LocalDateTime ora) {
        return binarysearch(ora);
    }

    /**
     * Questo metodo cerca l'orario di inizio e fine che contiene l'orario passato come parametro
     * @param s orario da cercare
     * @return l'orario di inizio e fine che contiene l'orario passato come parametro
     */
    private OrarioInizioFine binarysearch(LocalDateTime s){
        int i = 0;
        int j = this.orarioInizioFineList.size()-1;
        int m = (i+j)/2;
        while(i<=j){
            if(this.orarioInizioFineList.get(m).contiene(s)){
                return this.orarioInizioFineList.get(m);
            }
            if(this.orarioInizioFineList.get(m).inizio().isAfter(s)){
                j = m-1;
            }else{
                i = m+1;
            }
        }
        return this.orarioInizioFineList.get(m);
    }

    /**
     * Solo per test
     * @return la lista degli orari di inizio e fine
     */
    public List<OrarioInizioFine> getOrarioInizioFineList() {
        return orarioInizioFineList;
    }

}
