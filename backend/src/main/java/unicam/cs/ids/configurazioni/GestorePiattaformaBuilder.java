package unicam.cs.ids.configurazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unicam.cs.ids.repositorys.*;

@Service
public class GestorePiattaformaBuilder {

    private ContenutiRepository contenutiRepository;

    private ComuniRepository comuniRepository;

    private ContestRepository contestRepository;

    private PuntiFisiciRepository puntiFisiciRepository;

    private RichiesteRepository richiesteRepository;

    private UtentiRepository utentiRepository;

    @Autowired
    public GestorePiattaformaBuilder(ComuniRepository comuniRepository, RichiesteRepository richiesteRepository, ContenutiRepository contenutiRepository, ContestRepository contestRepository, PuntiFisiciRepository puntiFisiciRepository, UtentiRepository utentiRepository) {
        this.comuniRepository = comuniRepository;
        this.richiesteRepository = richiesteRepository;
        this.contenutiRepository = contenutiRepository;
        this.contestRepository = contestRepository;
        this.puntiFisiciRepository = puntiFisiciRepository;
        this.utentiRepository = utentiRepository;
    }

    public ContenutiRepository getContenutiRepository() {
        return contenutiRepository;
    }

    public void setContenutiRepository(ContenutiRepository contenutiRepository) {
        this.contenutiRepository = contenutiRepository;
    }

    public ComuniRepository getComuniRepository() {
        return comuniRepository;
    }

    public void setComuniRepository(ComuniRepository comuniRepository) {
        this.comuniRepository = comuniRepository;
    }

    public ContestRepository getContestRepository() {
        return contestRepository;
    }

    public void setContestRepository(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    public PuntiFisiciRepository getPuntiFisiciRepository() {
        return puntiFisiciRepository;
    }

    public void setPuntiFisiciRepository(PuntiFisiciRepository puntiFisiciRepository) {
        this.puntiFisiciRepository = puntiFisiciRepository;
    }

    public RichiesteRepository getRichiesteRepository() {
        return richiesteRepository;
    }

    public void setRichiesteRepository(RichiesteRepository richiesteRepository) {
        this.richiesteRepository = richiesteRepository;
    }

    public UtentiRepository getUtentiRepository() {
        return utentiRepository;
    }

    public void setUtentiRepository(UtentiRepository utentiRepository) {
        this.utentiRepository = utentiRepository;
    }
}
