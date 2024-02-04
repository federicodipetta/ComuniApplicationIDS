package unicam.cs.ids.models.richieste;

import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.stato.Stato;

public class Segnalazione extends RichiestaAstratta {

    private final String descrizione;
    private final Contenuto contenuto;

    /**
     * Costruttore di una segnalazione
     * @param descrizione la descrizione della segnalazione
     * @param contenuto il contenuto che deve essere segnalato
     */
    Segnalazione(String id, String descrizione, Contenuto contenuto){
        super(id);
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

    @Override
    public JSONObject dettagliMinimi() {
        try {
            return new JSONObject().put("tipo", "segnalazione")
                    .put("descrizione", descrizione);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject dettagli() {
        try {
            return new JSONObject().put("tipo", "segnalazione")
                    .put("descrizione", descrizione)
                    .put("contenuto", contenuto.dettagli());
        } catch (JSONException e) {
            throw new RuntimeException();
        }
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
