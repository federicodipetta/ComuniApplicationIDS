package unicam.cs.ids.models.controllers;

import org.json.JSONArray;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.punti.*;
import unicam.cs.ids.models.ruoli.GestoreComuni;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;

import java.util.ArrayList;
import java.util.List;

public class ControllerElementi {
    GestoreComuni gestoreComuni;

    public ControllerElementi() {
        this.gestoreComuni = GestorePiattaforma.getInstance().getGestoreComuni();
    }

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

    public List<Iscrizione> getIscrizioniVincenti(String idContest){
        return gestoreComuni.getGestoriComunali().stream()
                .filter(x->x.getContestById(idContest)!=null)
                .findFirst()
                .orElseThrow()
                .getIscrizioniVincenti(gestoreComuni.getGestoreComunale(
                                gestoreComuni.getComuneById(idContest)
                        ).getContestById(idContest));
    }

    public List<Iscrizione> getIscrizioniVincenti(String idContest, String idComune){
        Comune comune = gestoreComuni.getComuneById(idComune);
        return gestoreComuni.getGestoreComunale(comune)
                .getIscrizioniVincenti(gestoreComuni.getGestoreComunale(comune).getContestById(idContest));
    }

    public List<Contenuto> getContenuti(String idComune, PuntoFisico puntoFisico) {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune)).getDettagliContenuti(puntoFisico);
    }
}
