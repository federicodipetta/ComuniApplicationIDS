package unicam.cs.ids.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import unicam.cs.ids.Comune;
import unicam.cs.ids.punti.Contenuto;
import unicam.cs.ids.punti.Contest;
import unicam.cs.ids.punti.Iscrizione;
import unicam.cs.ids.punti.PuntoFisico;
import unicam.cs.ids.ruoli.GestoreComuni;

import java.util.List;

public class ControllerElementi {
    GestoreComuni gestoreComunale;
    public boolean aggiungiContenuto(Contenuto contenuto, String idComune, PuntoFisico puntoFisico) {
        return gestoreComunale.getGestoreComunale(gestoreComunale.getComuneById(idComune))
                .aggiungiContenuto(contenuto, puntoFisico);
    }
    public boolean aggiungiContest(Contest contest,String idComune){
        return gestoreComunale.getGestoreComunale(gestoreComunale.getComuneById(idComune))
                .aggiungiContest(contest);
    }
    public boolean eliminaContenuto(Contenuto contenuto, String idComune, PuntoFisico puntoFisico) {
        return gestoreComunale.getGestoreComunale(gestoreComunale.getComuneById(idComune))
                .eliminaContenuto(contenuto, puntoFisico);
    }

    public List<Iscrizione> getIscrizioniVincenti(String idContest){
        //TODO : implementare
        return null;
    }

    public List<Iscrizione> getIscrizioniVincenti(String idContest, String idComune){
        Comune comune = gestoreComunale.getComuneById(idComune);
        return gestoreComunale.getGestoreComunale(comune)
                .getIscrizioniVincenti(gestoreComunale.getGestoreComunale(comune).getContestById(idContest));
    }

    public JSONArray getContenuti(String idComune, PuntoFisico puntoFisico) throws JSONException {
        return gestoreComunale.getGestoreComunale(gestoreComunale.getComuneById(idComune)).getDettagliContenuti(puntoFisico);
    }
}
