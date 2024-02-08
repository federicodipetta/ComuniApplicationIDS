package unicam.cs.ids.models.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.punti.*;
import unicam.cs.ids.models.ruoli.GestoreComuni;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;
import unicam.cs.ids.repositorys.*;
import unicam.cs.ids.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ControllerElementi {

    GestoreComuni gestoreComuni;
    ContenutiRepository contenutiRepository;


    public ControllerElementi() { }

    @Autowired
    public ControllerElementi(ContenutiRepository contenutiRepository, GestorePiattaforma gestorePiattaforma) throws JSONException {
        this.gestoreComuni = gestorePiattaforma.getGestoreComuni();
        this.contenutiRepository = contenutiRepository;
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


    public Set<Contenuto> getContenuti(String idComune, PuntoFisico puntoFisico) {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune)).getDettagliContenuti(puntoFisico);
    }

    public List<PuntoFisico> getPunti(String idComune) {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune)).getPuntiFisici().stream().toList();
    }

    @JsonView(View.Dettagli.class)
    public Contenuto getContenuto(String idComune, String idContenuto) {
        System.out.println(gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune)).getContenutoById(idContenuto));
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune)).getContenutoById(idContenuto);
    }

}
