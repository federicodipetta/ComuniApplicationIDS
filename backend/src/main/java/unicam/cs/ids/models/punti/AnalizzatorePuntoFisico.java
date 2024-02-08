package unicam.cs.ids.models.punti;

import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.servizi.IServizioOSM;

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
    public boolean controllaPuntoFisico(PuntoFisico puntoFisico, Comune comune) {
        return getNomeComune(puntoFisico).equalsIgnoreCase(comune.nome().trim());
    }


    @Override
    public String getNomeComune(PuntoFisico puntoFisico) {
        JSONObject risultatoChiamata = null;
        try {
            risultatoChiamata = new JSONObject(servizioOSM.getInfoPunto(puntoFisico.getCoordinate()));
            JSONObject address = risultatoChiamata.getJSONObject("address");
            if(address.has("city")) return address.getString("city").trim();
            if(address.has("town")) return address.getString("town").trim();
            return "";
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return "";
        }
    }

}
