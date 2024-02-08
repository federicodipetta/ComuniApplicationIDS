package unicam.cs.ids.restControllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.cs.ids.configurazioni.GestorePiattaformaBuilder;
import unicam.cs.ids.dtos.ComuneDto;
import unicam.cs.ids.models.Comune;
import unicam.cs.ids.models.controllers.ControllerComuni;
import unicam.cs.ids.models.punti.PuntoFisico;
import unicam.cs.ids.models.servizi.ServizioOSM;
import unicam.cs.ids.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v0/comuni")
public class ComuniRestController {
    private ControllerComuni controllerComuni;

    @Autowired
    public ComuniRestController(GestorePiattaformaBuilder builder) {
        controllerComuni = new ControllerComuni(builder);
    }
    @JsonView(View.Dettagli.class)
    @GetMapping("/")
    public ResponseEntity<Set<Comune>> getComuni() {
        return ResponseEntity.ok(controllerComuni.getComuni());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addComune(@RequestBody ComuneDto comune) throws JSONException, IOException {
        ServizioOSM servizioOSM = new ServizioOSM();
        boolean bool = this.controllerComuni.aggiungiComune(
                new Comune(comune.nome(),
                        comune.provincia(),
                        comune.id(),
                        new PuntoFisico(
                                servizioOSM.getCoordinate(comune.nome()),
                                new HashSet<>()
                        )
                )
        );
        if(bool)
            return ResponseEntity.ok("Comune aggiunto");
        else
            return ResponseEntity.badRequest().body("Comune non aggiunto");
    }

}
