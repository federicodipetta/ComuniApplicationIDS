package unicam.cs.ids.restControllers;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.cs.ids.dtos.NotificaDto;
import unicam.cs.ids.dtos.RuoloDto;
import unicam.cs.ids.dtos.UtenteDto;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.controllers.ControllerUtenti;
import unicam.cs.ids.models.ruoli.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v0/utenti")
public class UtentiRestController {

    public ControllerUtenti controllerUtenti = new ControllerUtenti();

    public UtentiRestController() {
    }

    @PostMapping("/aggiungiUtente")
    public ResponseEntity<Object> aggiungiUtente(@RequestBody UtenteDto utente) {
        boolean risultato = controllerUtenti.aggiungiUtente(new Utente(utente.nomeUtente(), utente.id()));
        if(risultato) return new ResponseEntity<>("Utente aggiunto correttamente.", HttpStatus.OK);
        else return new ResponseEntity<>("Errore nell'aggiunta dell'utente.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/rimuoviUtente")
    public ResponseEntity<Object> rimuoviUtente(@PathParam("id") String id) {
        boolean risultato = controllerUtenti.rimuoviUtente(id);
        if(risultato) return new ResponseEntity<>("Utente rimosso correttamente.", HttpStatus.OK);
        else return new ResponseEntity<>("Errore nella rimozione dell'utente.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/setRuoloUtente")
    public ResponseEntity<Object> setRuoloUtente(@PathParam("id") String id, @RequestBody RuoloDto ruolo) {
        boolean risultato = controllerUtenti.setRuoloUtente(id, new RuoloComune(ruolo.comune(), ruolo.ruolo()));
        if(risultato) return new ResponseEntity<>("Ruolo utente aggiornato correttamente.", HttpStatus.OK);
        else return new ResponseEntity<>("Errore nell'aggiornamento del ruolo utente.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getComuniAbilitati")
    public ResponseEntity<Set<Comune>> getComuniAbilitati(@PathParam("id") String id, @PathParam("ruolo") Ruolo ruolo) {
        return new ResponseEntity<>(controllerUtenti.getComuniAbilitati(id, ruolo), HttpStatus.OK);
    }

    @PostMapping("/addNotifica")
    public ResponseEntity<Object> addNotifica(@PathParam("id") String id, @RequestBody NotificaDto notifica) {
        boolean risultato = controllerUtenti.invioNotifica(id, new Notifica(notifica.testo(), notifica.id()));
        if(risultato) return new ResponseEntity<>("Notifica aggiunta correttamente.", HttpStatus.OK);
        else return new ResponseEntity<>("Errore nell'aggiunta della notifica.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getNotifiche")
    public ResponseEntity<Set<Notifica>> getNotifiche(@PathParam("id") String id) {
        return new ResponseEntity<>(controllerUtenti.getNotifiche(id), HttpStatus.OK);
    }

    @DeleteMapping("/rimuoviNotifica")
    public ResponseEntity<Object> rimuoviNotifica(@PathParam("id") String id, @PathParam("idNotifica") String idNotifica) {
        boolean risultato = controllerUtenti.rimuoviNotifica(id, new Notifica("", idNotifica));
        if(risultato) return new ResponseEntity<>("Notifica rimossa correttamente.", HttpStatus.OK);
        else return new ResponseEntity<>("Errore nella rimozione della notifica.", HttpStatus.BAD_REQUEST);
    }

}
