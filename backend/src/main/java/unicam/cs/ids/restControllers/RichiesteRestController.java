package unicam.cs.ids.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.dtos.*;
import unicam.cs.ids.mappers.RichiesteMapper;
import unicam.cs.ids.models.controllers.ControllerRichieste;
import unicam.cs.ids.models.richieste.RichiestaAstratta;
import unicam.cs.ids.view.View;

import java.util.Collection;

@RestController
@RequestMapping("/api/v0/richieste")
public class RichiesteRestController {
    private ControllerRichieste controllerRichieste;

    private RichiesteMapper richiesteMapper;
    @Autowired
    public RichiesteRestController(ControllerRichieste controllerRichieste, RichiesteMapper richiesteMapper) {
        this.controllerRichieste = controllerRichieste;
        this.richiesteMapper = richiesteMapper;
    }
    @PostMapping("/contenuti/aggiungi")
    public ResponseEntity<Object> aggiungiRichiestaAggiunta(@RequestBody RichiestaContenutoAggiuntaDto richiestaContenutoDto
                                    ,@PathParam("id") String id){
        boolean response = controllerRichieste.aggiungiRichiestaAggiunta(
                        RichiesteMapper.mapRichiestaAggiunta(richiestaContenutoDto, id),
                        id);

        if(response)
            return new ResponseEntity<>("Richiesta aggiunta correttamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("Errore nell'aggiunta della richiesta", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/contenuti/eliminazione")
    public ResponseEntity<Object> aggiungiRichiestaRimozione(@RequestBody RichiestaEliminazioneDto richiestaContenutoDto
                                    ,@PathParam("id") String id){
        boolean response = controllerRichieste.aggiuntaRichiestaEliminazione(
                RichiesteMapper.mapRichiestaEliminaContenuto(richiestaContenutoDto,id),
                id);

        if(response)
            return new ResponseEntity<>("Richiesta aggiunta correttamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("Errore nell'aggiunta della richiesta", HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/contest/iscrizione")
    public ResponseEntity<Object> iscrizioneContest(@RequestBody IscrizioneDto iscrizioneDto,
                                                    @RequestParam("file") MultipartFile file,
                                                    @PathParam("id") String id){
        boolean response = controllerRichieste.aggiungiRichiestaIscrizione(
                RichiesteMapper.mapRichiestaIscrizione(iscrizioneDto, file,id),
                id);
      
        if(response)
            return new ResponseEntity<>("Richiesta aggiunta correttamente", HttpStatus.OK);
        else 
            return new ResponseEntity<>("Errore nell'aggiunta della richiesta", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/contenuti/file")
    public ResponseEntity<Object> aggiungiFile(@RequestParam("file") MultipartFile file,
                                              @RequestBody RichiestaFileDto richiestaFileDto,
                                              @PathParam("id") String id){
        boolean response = controllerRichieste.aggiungiRichiesta(
                RichiesteMapper.mapRichiestaFile(richiestaFileDto,file,id)
                ,id);

        if(response)
            return new ResponseEntity<>("File aggiunto correttamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("Errore nell'aggiunta del file", HttpStatus.BAD_REQUEST);
    }

    @PostMapping ("/segnalazione/aggiungi")
    public ResponseEntity<Object> aggiungiSegnalazione(@RequestBody SegnalazioneDto segnalazioneDto,
                                                       @PathParam("id") String id){
        boolean response = controllerRichieste.aggiungiSegnalazione(
                RichiesteMapper.mapSegnalazione(id,segnalazioneDto)
                ,id);

        if(response)
            return new ResponseEntity<>("Segnalazione aggiunta correttamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("Errore nell'aggiunta della segnalazione", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/valuta")
    public ResponseEntity<Object> valutaRichiesta(@PathParam("id") String id, @RequestBody ValutazioneDto valutazioneDto){
        boolean response = controllerRichieste.valutaRichiesta(RichiesteMapper.richiestaCommand(
                id,valutazioneDto.id()
                )
                ,id
                ,valutazioneDto.risposta());

        if(response)
            return new ResponseEntity<>("Richiesta valutata correttamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("Errore nella valutazione della richiesta", HttpStatus.BAD_REQUEST);

    }
    @JsonView(View.DettagliMinimi.class)
    @GetMapping("")
    public ResponseEntity<Collection<RichiestaAstratta>> getRichieste(@PathParam("id") String id){
        return new ResponseEntity<>(controllerRichieste.getRichieste(id), HttpStatus.OK);
    }

    @JsonView(View.Dettagli.class)
    @GetMapping("/getRichiesta")
    public ResponseEntity<Object> getRichiesta(@PathParam("id") String id,@PathParam("idR") String idR){
        return new ResponseEntity<>(controllerRichieste.getRichiesta(idR, id), HttpStatus.OK);
    }



}
