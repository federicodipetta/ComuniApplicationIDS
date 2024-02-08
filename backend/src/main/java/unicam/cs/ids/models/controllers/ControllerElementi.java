package unicam.cs.ids.models.controllers;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.punti.*;
import unicam.cs.ids.models.ruoli.GestoreComuni;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;
import unicam.cs.ids.models.ruoli.Utente;
import unicam.cs.ids.models.tempo.SempreAttivo;
import unicam.cs.ids.repositorys.ContenutiRepository;
import unicam.cs.ids.repositorys.ContestRepository;
import unicam.cs.ids.repositorys.PuntiFisiciRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class ControllerElementi {
    GestoreComuni gestoreComuni;
    ContenutiRepository contenutiRepository;
    PuntiFisiciRepository repo;

    ContestRepository contestRepository;
    public ControllerElementi() {
    }
    @Autowired
    public ControllerElementi(ContenutiRepository contenutiRepository, ContestRepository contestRepository, PuntiFisiciRepository repo) throws JSONException {
        this.contestRepository = contestRepository;
        this.gestoreComuni = GestorePiattaforma.getInstance().getGestoreComuni();
        this.contenutiRepository = contenutiRepository;
        PuntoInteresse municipio= new PuntoInteresse("Municipio", "Sede del comune", new ArrayList<>());
        this.contenutiRepository.save(
                new PuntoInteresse("Piazza", "Piazza principale", new ArrayList<>()));
        this.contenutiRepository.save(municipio);
        Contenuto c = this.contenutiRepository.findAll().iterator().next();
        Itinerario it = new Itinerario("Itinerario", "Itinerario turistico", new ArrayList<>(), new ArrayList<>(List.of(c)), new SempreAttivo());
        this.contenutiRepository.save(it);
        this.repo = repo;
        this.repo.save(new PuntoFisico(new Coordinate(1.0, 1.0), Set.of(municipio)));
        this.contestRepository.save(new Contest(new Utente("eduardo"), "Contest", "descrizione del mio contest", new SempreAttivo(), this.repo.findAll().getFirst()));
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

    public Contenuto getContenuto(String idComune, String idContenuto) {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune)).getContenutoById(idContenuto);
    }

}
