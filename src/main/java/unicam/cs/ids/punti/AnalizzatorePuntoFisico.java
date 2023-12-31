package unicam.cs.ids.punti;

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
     */
    public boolean controllaPuntoFisico(PuntoFisico puntoFisico, Comune comune) throws IOException {
        String risultatoChiamata = servizioOSM.getInfoPunto(puntoFisico.getCoordinate());
        String risultato = getNomeCitta(risultatoChiamata);
        if(risultato == null) risultato = getNomeCitta2(risultatoChiamata);
        if(risultato == null) return false;
        return risultato.equals("\"" + comune.getNome() + "\"");
    }

    /**
     * Restituisce il nome della città in cui si trova il punto fisico.
     * @param risultatoChiamata Il risultato della chiamata al servizio OSM.
     * @return Il nome della città in cui si trova il punto fisico.
     */
    private String getNomeCitta(String risultatoChiamata) {
        if(risultatoChiamata.split("town").length < 2) return null;
        if(risultatoChiamata.split("town")[1].split(":").length < 2) return null;
        return risultatoChiamata
                .split("town")[1]
                .split(":")[1]
                .split(",")[0];
    }

    /**
     * Restituisce il nome della città in cui si trova il punto fisico.
     * @param risultatoChiamata Il risultato della chiamata al servizio OSM.
     * @return Il nome della città in cui si trova il punto fisico.
     */
    private String getNomeCitta2(String risultatoChiamata) {
        if(risultatoChiamata.split("city").length < 2) return null;
        if(risultatoChiamata.split("city")[1].split(":").length < 2) return null;
        return risultatoChiamata
                .split("city")[1]
                .split(":")[1]
                .split(",")[0];
    }

}
