package unicam.cs.ids.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.websocket.server.PathParam;
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
import java.util.List;

@RestController
@RequestMapping("/api/v0/richieste")
public class RichiesteRestController {
    private ControllerRichieste controllerRichieste = new ControllerRichieste();
    public RichiesteRestController() {
    }
    @PostMapping("/contenuti/aggiungi")
    public ResponseEntity<Object> aggiungiRichiestaAggiunta(@RequestBody RichiestaContenutoAggiuntaDto richiestaContenutoDto
                                    ,@PathParam("id") String id){

        return new ResponseEntity<>(
                controllerRichieste.aggiungiRichiestaAggiunta(
                        RichiesteMapper.mapRichiestaAggiunta(richiestaContenutoDto, id),
                        id
                )
                , HttpStatus.OK);
    }

    @PostMapping("/contenuti/eliminazione")
    public ResponseEntity<Object> rimuoviRichiestaAggiunta(@RequestBody RichiestaEliminazioneDto richiestaContenutoDto
                                    ,@PathParam("id") String id){

        boolean reponse= controllerRichieste.aggiuntaRichiestaEliminazione(
                RichiesteMapper.mapRichiestaEliminaContenuto(richiestaContenutoDto,id),
                id
        );
        if(reponse)
            return new ResponseEntity<>("Richiesta aggiunta correttamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("Errore nell'aggiunta della richiesta", HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/contest/iscrizione")
    public ResponseEntity<Object> iscrizioneContest(@RequestBody IscrizioneDto iscrizioneDto,
                                                    @RequestParam("file") MultipartFile file,
                                                    @PathParam("id") String id){
        boolean reponse= controllerRichieste.aggiungiRichiestaIscrizione(
                RichiesteMapper.mapRichiestaIscrizione(iscrizioneDto, file,id),
                id
        );
        if(reponse)
            return new ResponseEntity<>("Richiesta aggiunta correttamente", HttpStatus.OK);
        else 
            return new ResponseEntity<>("Errore nell'aggiunta della richiesta", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/contenuti/file")
    public ResponseEntity<Object> aggiungiFile(@RequestParam("file") MultipartFile file,
                                              @RequestBody RichiestaFileDto richiestaFileDto,
                                              @PathParam("id") String id){
        boolean reponse= controllerRichieste.aggiungiRichiesta(
                RichiesteMapper.mapRichiestaFile(richiestaFileDto,file,id)
                ,id);
        if(reponse)
            return new ResponseEntity<>("File aggiunto correttamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("Errore nell'aggiunta del file", HttpStatus.BAD_REQUEST);
    }

    @PostMapping ("/segnalazione/aggiungi")
    public ResponseEntity<Object> aggiungiSegnalazione(@RequestBody SegnalazioneDto segnalazioneDto,
                                                       @PathParam("id") String id){
        boolean reponse= controllerRichieste.aggiungiSegnalazione(
                RichiesteMapper.mapSegnalazione(id,segnalazioneDto)
                ,id);
        if(reponse)
            return new ResponseEntity<>("Segnalazione aggiunta correttamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("Errore nell'aggiunta della segnalazione", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/valuta")
    public ResponseEntity<Object> valutaRichiesta(@PathParam("id") String id, @RequestBody ValutazioneDto valutazioneDto){
        boolean reponse= controllerRichieste.valutaRichiesta(RichiesteMapper.richiestaCommand(
                id,valutazioneDto.id()
                )
                ,id
                ,valutazioneDto.risposta());
        if(reponse)
            return new ResponseEntity<>("Richiesta valutata correttamente", HttpStatus.OK);
        else
            return new ResponseEntity<>("Errore nella valutazione della richiesta", HttpStatus.BAD_REQUEST);

    }
    @JsonView(View.DettagliMinimi.class)
    @GetMapping("")
    public ResponseEntity<Collection<RichiestaAstratta>> getRichieste(@PathParam("id") String id){
        return new ResponseEntity<>(controllerRichieste.getRichieste(id), HttpStatus.OK);
    }

    @GetMapping("/getRichiesta")
    public ResponseEntity<Object> getRichiesta(@PathParam("id") String id,@PathParam("idR") String idR){
        return new ResponseEntity<>(controllerRichieste.getRichiesta(id,idR), HttpStatus.OK);
    }



}
