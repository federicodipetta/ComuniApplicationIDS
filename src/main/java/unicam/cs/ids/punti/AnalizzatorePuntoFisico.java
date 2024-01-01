package unicam.cs.ids.punti;

import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.Comune;
import unicam.cs.ids.servizi.ServizioOSM;
import java.io.IOException;

/**
 * Questa classe viene usata per analizzare un punto fisico.
 */
public class AnalizzatorePuntoFisico {
    private final ServizioOSM servizioOSM;

    public AnalizzatorePuntoFisico() {
        this.servizioOSM = new ServizioOSM();
    }

    /**
     * Controlla se il punto fisico è all'interno del comune.
     * @param puntoFisico Il punto fisico da controllare.
     * @param comune Il comune in cui controllare.
     * @return True se il punto fisico è all'interno del comune, false altrimenti.
     * @throws IOException Se la chiamata al servizio OSM fallisce.
     * @throws JSONException Se la risposta del servizio OSM non è in formato JSON.
     */
    public boolean controllaPuntoFisico(PuntoFisico puntoFisico, Comune comune) throws IOException, JSONException {
        JSONObject risultatoChiamata = new JSONObject(servizioOSM.getInfoPunto(puntoFisico.getCoordinate()));
        JSONObject address = risultatoChiamata.getJSONObject("address");
        if(address.has("city")) return address.getString("city").equalsIgnoreCase(comune.getNome());
        if(address.has("town")) return address.getString("town").equalsIgnoreCase(comune.getNome());
        return false;
    }

}
