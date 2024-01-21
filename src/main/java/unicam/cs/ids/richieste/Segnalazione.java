package unicam.cs.ids.richieste;

import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.punti.Contenuto;
import unicam.cs.ids.ruoli.GestoreComunale;
import unicam.cs.ids.stato.Stato;

public class Segnalazione implements RichiestaCommand{

    private String descrizione;
    private Contenuto contenuto;

    /**
     * Costruttore di una segnalazione
     * @param descrizione la descrizione della segnalazione
     * @param contenuto il contenuto che deve essere segnalato
     */
    Segnalazione(String descrizione, Contenuto contenuto){
        this.descrizione = descrizione;
        this.contenuto = contenuto;
    }
    @Override
    public void esegui(boolean accetta) {
        if(accetta){
            this.contenuto.setStato(Stato.ELIMINATO);
        }
        //in caso non sia accettata andrebbe implementata la notificazione dell'utente

    }


    public String toString(){
        try {
            return new JSONObject().put("descrizione", descrizione)
                    .put("contenuto", contenuto.dettagli())
                    .toString(0);
        } catch (JSONException e) {
            return descrizione;
        }
    }
}
