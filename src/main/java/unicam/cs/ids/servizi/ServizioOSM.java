package unicam.cs.ids.servizi;

import unicam.cs.ids.punti.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Classe utilizzata per ottenere informazioni da OpenStreetMap.
 */
public class ServizioOSM implements IServizioOSM {

    @Override
    public String getInfoPunto(Coordinate coordinate) throws IOException {
        URLConnection connessione = getUrlConnection(coordinate);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
        String line;
        StringBuilder builder = new StringBuilder();
        while((line = reader.readLine()) != null) builder.append(line);
        reader.close();
        return builder.toString();
    }

    private URLConnection getUrlConnection(Coordinate coordinate) throws IOException {
        String link = "https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=" + coordinate.latitudine().toString();
        link += "&lon=" + coordinate.longitudine().toString();
        return new URL(link).openConnection();
    }

}
