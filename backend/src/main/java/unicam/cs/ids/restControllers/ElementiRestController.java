package unicam.cs.ids.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.cs.ids.dtos.ContestDto;
import unicam.cs.ids.dtos.PuntoFisicoDto;
import unicam.cs.ids.models.controllers.ControllerElementi;
import unicam.cs.ids.models.punti.*;
import unicam.cs.ids.view.View;
import unicam.cs.ids.wrappers.ContenutoPuntoFisicoWrapper;

import java.util.ArrayList;
import java.util.HashSet;

@RestController
@RequestMapping("/api/v0/elementi")
@CrossOrigin(origins = "http://localhost:4200")
public class ElementiRestController {

    private final ControllerElementi controllerElementi = new ControllerElementi();

    public ElementiRestController() {
    }

    @PostMapping("/aggiungiContenuto")
    public ResponseEntity<Object> aggiungiContenuto(@RequestBody ContenutoPuntoFisicoWrapper wrapper, @PathParam("id") String id) {
        ContenutoBuilder builder = new ContenutoBuilder();
        Contenuto contenuto = builder
                .setId(wrapper.contenuto().getId())
                .setTitolo(wrapper.contenuto().getTitolo())
                .setTesto(wrapper.contenuto().getTesto())
                .setStato(wrapper.contenuto().getStato())
                .build();
        boolean risultato = controllerElementi.aggiungiContenuto(contenuto, id, wrapper.puntoFisico());
        if(risultato) return new ResponseEntity<>("Contenuto aggiunto correttamente.", HttpStatus.OK);
        else return new ResponseEntity<>("Errore nell'aggiunta del contenuto.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/aggiungiContest")
    public ResponseEntity<Object> aggiungiContest(ContestDto contest, @PathParam("id") String idComune) {
        Contest con = new Contest(contest.getAnimatore(), contest.getTitolo(), contest.getDescrizione(), contest.getTempo(), contest.getPuntoFisico(), contest.getId());
        boolean risultato = controllerElementi.aggiungiContest(con, idComune);
        if(risultato) return new ResponseEntity<>("Contest aggiunto correttamente.", HttpStatus.OK);
        else return new ResponseEntity<>("Errore nell'aggiunta del contest.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminaContenuto")
    public ResponseEntity<Object> eliminaContenuto(ContenutoPuntoFisicoWrapper wrapper, @PathParam("id") String idComune) {
        ContenutoBuilder builder = new ContenutoBuilder();
        Contenuto contenuto = builder
                .setId(wrapper.contenuto().getId())
                .setTitolo(wrapper.contenuto().getTitolo())
                .setTesto(wrapper.contenuto().getTesto())
                .setIscritti(wrapper.contenuto().getIscritti())
                .setStato(wrapper.contenuto().getStato())
                .setContenuti(wrapper.contenuto().contenuti(), wrapper.contenuto().ordianto())
                .build();
        boolean risultato = controllerElementi.eliminaContenuto(contenuto, idComune, wrapper.puntoFisico());
        if(risultato) return new ResponseEntity<>("Contenuto eliminato correttamente.", HttpStatus.OK);
        else return new ResponseEntity<>("Errore nell'eliminazione del contenuto.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getIscrizioniVincenti")
    public ResponseEntity<Object> getIscrizioniVincenti(@PathParam("id") String idContest) {
        return new ResponseEntity<>(controllerElementi.getIscrizioniVincenti(idContest), HttpStatus.OK);
    }

    // Senza il 2 non riesce ad avviare il progetto.
    @GetMapping("/getIscrizioniVincenti2")
    public ResponseEntity<Object> getIscrizioniVincenti(@PathParam("idContest") String idContest, @PathParam("idComune") String idComune) {
        return new ResponseEntity<>(controllerElementi.getIscrizioniVincenti(idContest, idComune), HttpStatus.OK);
    }

    @JsonView(View.DettagliMinimi.class)
    @GetMapping("/getContenuti")
    public ResponseEntity<Object> getContenuti(@PathParam("id") String id, @PathParam("lat") String lat, @PathParam("lon") String lon) {
        PuntoFisico puntoFisico1 = new PuntoFisico(new Coordinate(Double.parseDouble(lat), Double.parseDouble(lon)), new HashSet<>());
        return new ResponseEntity<>(controllerElementi.getContenuti(id, puntoFisico1), HttpStatus.OK);
    }

    @GetMapping("/getContenuto")
    public ResponseEntity<Object> getContenuto(@PathParam("idComune") String idComune, @PathParam("idContenuto") String idContenuto) {
        return new ResponseEntity<>(controllerElementi.getContenuto(idComune, idContenuto), HttpStatus.OK);
    }

    @GetMapping("/getPunti")
    @JsonView(View.DettagliMinimi.class)
    public ResponseEntity<Object> getPunti(@PathParam("id") String id) {
        return new ResponseEntity<>(controllerElementi.getPunti(id), HttpStatus.OK);
    }

}
