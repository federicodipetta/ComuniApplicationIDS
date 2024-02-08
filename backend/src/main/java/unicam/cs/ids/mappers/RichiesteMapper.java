package unicam.cs.ids.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.dtos.*;
import unicam.cs.ids.models.richieste.*;
import unicam.cs.ids.models.ruoli.GestoreComunale;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;

import java.util.List;

@Component
public class RichiesteMapper {

    private GestorePiattaforma gestorePiattaforma;

    @Autowired
    public RichiesteMapper(GestorePiattaforma gestorePiattaforma) {
        this.gestorePiattaforma = gestorePiattaforma;
    }

    public RichiestaContenuto mapRichiestaAggiunta(RichiestaContenutoAggiuntaDto richiestaContenutoDto, String idComune){
        return new RichiestaContenuto(
                ContenutoMapper.mapContenutoRichiesta(richiestaContenutoDto.contenuto()),
                PuntoFisicoMapper.mapPuntoFisico(richiestaContenutoDto.puntoFisico())
        );
    }

    public RichiestaEliminaContenuto mapRichiestaEliminaContenuto(RichiestaEliminazioneDto richiestaEliminazioneDto,String idComune){
        return new RichiestaEliminaContenuto(
                    richiestaEliminazioneDto.id(),
                    getGestoreComunale(idComune).getContenutoById(richiestaEliminazioneDto.idContenuto()),
                    PuntoFisicoMapper.mapPuntoFisico(richiestaEliminazioneDto.puntoFisico())
            );
    }

    public RichiestaIscrizione mapRichiestaIscrizione(IscrizioneDto iscrizioneDto, MultipartFile file, String idComune){
        return new RichiestaIscrizione(
                file,
                getGestoreComunale(idComune).getContestById(iscrizioneDto.idContest()),
                GestorePiattaforma.getInstance().getGestoreUtenti().getUtenteById(iscrizioneDto.idUtente())
        );
    }

    public RichiestaFile mapRichiestaFile(RichiestaFileDto richiestaFileDto, MultipartFile file, String idComune){
        return new RichiestaFile(
                richiestaFileDto.id(),
                List.of(file),
                getGestoreComunale(idComune).getContenutoById(richiestaFileDto.idContenuto())
        );
    }

    public Segnalazione mapSegnalazione(String idComune, SegnalazioneDto segnalazioneDto){
        return new Segnalazione(
                segnalazioneDto.id(),
                segnalazioneDto.descrizione(),
                getGestoreComunale(idComune).getContenutoById(segnalazioneDto.idContenuto())
        );
    }


    private GestoreComunale getGestoreComunale(String idComune) {
        return this.gestorePiattaforma.getGestoreComuni().getGestoreComunale(
                this.gestorePiattaforma.getGestoreComuni().getComuneById(idComune));
    }


    public RichiestaAstratta richiestaCommand(String comune, String id){
        return getGestoreComunale(comune).getGestoreRichieste().getRichiestaById(id);
    }

}