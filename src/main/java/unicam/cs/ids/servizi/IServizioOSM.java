package unicam.cs.ids.servizi;

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
    public String getInfoPunto(Coordinate coordinate) throws IOException;

}
