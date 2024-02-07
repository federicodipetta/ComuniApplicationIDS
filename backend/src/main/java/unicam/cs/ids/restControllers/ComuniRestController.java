package unicam.cs.ids.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.cs.ids.dtos.ComuneDto;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.controllers.ControllerComuni;
import unicam.cs.ids.models.punti.PuntoFisico;
import unicam.cs.ids.models.servizi.ServizioOSM;
import unicam.cs.ids.view.View;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v0/comuni")
public class ComuniRestController {

    private ControllerComuni controllerComuni = new ControllerComuni();

    public ComuniRestController() {
    }

    @JsonView(View.Dettagli.class)
    @GetMapping("/")
    public ResponseEntity<Set<Comune>> getComuni() {
        return ResponseEntity.ok(controllerComuni.getComuni());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addComune(@RequestBody ComuneDto comune) {
        boolean bool = false;

        try {
            ServizioOSM servizioOSM = new ServizioOSM();
            bool = this.controllerComuni.aggiungiComune(
                    new Comune(comune.nome(),
                            comune.provincia(),
                            comune.id(),
                            new PuntoFisico(
                                    servizioOSM.getCoordinate(comune.nome()),
                                    new HashSet<>()
                            )
                    )
            );
        } catch (Exception ignored) {}

        if(bool)
            return ResponseEntity.ok("Comune aggiunto");
        else
            return ResponseEntity.badRequest().body("Comune non aggiunto");
    }

}
