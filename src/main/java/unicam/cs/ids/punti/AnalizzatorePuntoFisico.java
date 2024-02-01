package unicam.cs.ids.punti;

import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.Comune;
import unicam.cs.ids.servizi.IServizioOSM;

import java.io.IOException;

/**
 * Questa classe viene usata per analizzare un punto fisico.
 */
public class AnalizzatorePuntoFisico implements IAnalizzatorePuntoFisico {

    private final IServizioOSM servizioOSM;

    public AnalizzatorePuntoFisico(IServizioOSM servizioOSM) {
            this.servizioOSM = servizioOSM;
    }

    @Override
    public boolean controllaPuntoFisico(PuntoFisico puntoFisico, Comune comune) throws IOException, JSONException {
        return getNomeComune(puntoFisico).equalsIgnoreCase(comune.nome().trim());
    }

    @Override
    public String getNomeComune(PuntoFisico puntoFisico) throws IOException, JSONException {
        JSONObject risultatoChiamata = new JSONObject(servizioOSM.getInfoPunto(puntoFisico.getCoordinate()));
        JSONObject address = risultatoChiamata.getJSONObject("address");
        if(address.has("city")) return address.getString("city").trim();
        if(address.has("town")) return address.getString("town").trim();
        return "";
    }

}
