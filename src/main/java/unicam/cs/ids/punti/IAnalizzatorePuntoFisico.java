package unicam.cs.ids.punti;

import org.json.JSONException;
import unicam.cs.ids.Comune;

import java.io.IOException;

public interface IAnalizzatorePuntoFisico {

    /**
     * Controlla se il punto fisico è all'interno del comune.
     * @param puntoFisico Il punto fisico da controllare.
     * @param comune Il comune in cui controllare.
     * @return True se il punto fisico è all'interno del comune, false altrimenti.
     * @throws IOException Se la chiamata al servizio OSM fallisce.
     * @throws JSONException Se la risposta del servizio OSM non è in formato JSON.
     */
    public boolean controllaPuntoFisico(PuntoFisico puntoFisico, Comune comune) throws IOException, JSONException;

    /**
     * Restituisce il nome del comune in cui si trova il punto fisico.
     */
    public String getNomeComune(PuntoFisico puntoFisico) throws IOException, JSONException;

}
