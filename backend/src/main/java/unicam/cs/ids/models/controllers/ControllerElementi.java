package unicam.cs.ids.models.controllers;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.cs.ids.configurazioni.GestorePiattaformaBuilder;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.punti.*;
import unicam.cs.ids.models.ruoli.GestoreComuni;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;
import unicam.cs.ids.models.ruoli.Utente;

import unicam.cs.ids.models.stato.Stato;
import unicam.cs.ids.models.tempo.SempreAttivo;
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
    ContestRepository contestRepository;
    PuntiFisiciRepository repo;

    GestorePiattaforma gestorePiattaforma;
    @Autowired
    public ControllerElementi(ContenutiRepository contenutiRepository, PuntiFisiciRepository repo
    , ContestRepository repository , UtentiRepository ur, IscrizioniRepository ir, GestorePiattaformaBuilder builder) {
        this.gestoreComuni = GestorePiattaforma.getInstance(builder).getGestoreComuni();
        System.out.println("Gestore comuni: "+gestoreComuni);
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
        this.contestRepository = repository;
        PuntoFisico puntoFisico = this.repo.findAll().iterator().next();
        Utente utente = new Utente("0");
        utente = ur.save(utente);

        this.contestRepository.save(new Contest(utente,"Concorso","Concorso di fotografia", new SempreAttivo(),puntoFisico));
        var z = this.contestRepository.findAll().getFirst();
        Iscrizione iz = new Iscrizione(utente, z,"bella");
        iz =  ir.save(iz);
        z.aggiungiIscrizione(iz);
        ir.save(iz);
        this.contestRepository.save(z);
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
        return this.gestoreComuni.getGestoreComunale(this.gestoreComuni.getComuneById(idComune)).getPuntiFisici().stream().toList();
    }

    public Contenuto getContenuto(String idComune, String idContenuto) {
        return gestoreComuni.getGestoreComunale(gestoreComuni.getComuneById(idComune)).getContenutoById(idContenuto);
    }

}
