package unicam.cs.ids.servizi;

import org.json.JSONException;
import unicam.cs.ids.punti.Coordinate;

import java.io.IOException;

/**
 * Interfaccia utilizzata per ottenere informazioni da OpenStreetMap.
 */
public interface IServizioOSM {

    /**
     * Metodo utilizzato per ottenere le informazioni di un punto
     * geografico da OpenStreetMap.
     * @param coordinate Le coordinate del punto di cui si vogliono ottenere le informazioni.
     * @return Le informazioni, in formato JSON, ottenute da OSM riguardanti il punto dato.
     * */
    String getInfoPunto(Coordinate coordinate) throws IOException;

    /**
     * Metodo utilizzato per ottenere le coordinate di un comune.
     * @param comune Il comune di cui si vogliono ottenere le coordinate.
     * @return Le coordinate del comune dato.
     * @throws IOException Se si verifica un errore di I/O.
     * @throws JSONException Se si verifica un errore di JSON.
     */
    Coordinate getCoordinate(String comune) throws IOException, JSONException;

}
