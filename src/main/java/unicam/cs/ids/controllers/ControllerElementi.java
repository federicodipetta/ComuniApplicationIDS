package unicam.cs.ids.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import unicam.cs.ids.Comune;
import unicam.cs.ids.punti.Contenuto;
import unicam.cs.ids.punti.Contest;
import unicam.cs.ids.punti.Iscrizioni;
import unicam.cs.ids.punti.PuntoFisico;
import unicam.cs.ids.ruoli.GestoreComuni;

import java.util.List;

public class ControllerElementi {
    GestoreComuni gestoreComuni;

    public ControllerElementi(GestoreComuni gestoreComuni) {
        this.gestoreComuni = gestoreComuni;
    }

    public boolean aggiungiContenuto(Contenuto contenuto, String idComune, PuntoFisico puntoFisico) {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .aggiungiContenuto(contenuto, puntoFisico);
    }
    public boolean aggiungiContest(Contest contest,String idComune){
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .aggiungiContest(contest);
    }
    public boolean eliminaContenuto(Contenuto contenuto, String idComune, PuntoFisico puntoFisico) {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune))
                .eliminaContenuto(contenuto, puntoFisico);
    }

    public List<Iscrizioni> getIscrizioniVincenti(String idContest){
        //TODO : implementare
        return null;
    }

    public List<Iscrizioni> getIscrizioniVincenti(String idContest, String idComune){
        Comune comune = gestoreComuni.getComuneById(idComune);
        return gestoreComuni.getGestoreComunale(comune)
                .getIscrizioniVincenti(gestoreComuni.getGestoreComunale(comune).getContestById(idContest));
    }

    public JSONArray getContenuti(String idComune, PuntoFisico puntoFisico) throws JSONException {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune)).getDettagliContenuti(puntoFisico);
    }
}
