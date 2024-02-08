package unicam.cs.ids.mappers;

import org.springframework.web.multipart.MultipartFile;
import unicam.cs.ids.dtos.*;
import unicam.cs.ids.models.richieste.*;
import unicam.cs.ids.models.ruoli.GestoreComunale;
import unicam.cs.ids.models.ruoli.GestorePiattaforma;

import java.util.List;

public class RichiesteMapper {



    public RichiesteMapper() {
    }

    public static RichiestaContenuto mapRichiestaAggiunta(
            RichiestaContenutoAggiuntaDto richiestaContenutoDto, String idComune){

        return new RichiestaContenuto(
                ContenutoMapper.mapContenutoRichiesta(richiestaContenutoDto.contenuto()),
                PuntoFisicoMapper.mapPuntoFisico(richiestaContenutoDto.puntoFisico())
        );
    }


    public static RichiestaEliminaContenuto mapRichiestaEliminaContenuto(
            RichiestaEliminazioneDto richiestaEliminazioneDto,String idComune){

            return new RichiestaEliminaContenuto(
                    richiestaEliminazioneDto.id(),
                    getGestoreComunale(idComune).getContenutoById(richiestaEliminazioneDto.idContenuto()),
                    PuntoFisicoMapper.mapPuntoFisico(richiestaEliminazioneDto.puntoFisico())
            );

    }

    public static RichiestaIscrizione mapRichiestaIscrizione(IscrizioneDto iscrizioneDto, MultipartFile file, String idComune){
        return new RichiestaIscrizione(
                file,
                getGestoreComunale(idComune).getContestById(iscrizioneDto.idContest()),
                GestorePiattaforma.getInstance().getGestoreUtenti().getUtenteById(iscrizioneDto.idUtente())
        );
    }


    public static RichiestaAstratta richiestaCommand(String comune, String id){
        return getGestoreComunale(comune).getGestoreRichieste().getRichiestaById(id);
    }

    public static RichiestaFile mapRichiestaFile(RichiestaFileDto richiestaFileDto, MultipartFile file, String idComune){
        return new RichiestaFile(
                richiestaFileDto.id(),
                List.of(file),
                getGestoreComunale(idComune).getContenutoById(richiestaFileDto.idContenuto())
        );
    }

    public static Segnalazione mapSegnalazione(String idComune, SegnalazioneDto segnalazioneDto){
        return new Segnalazione(
                segnalazioneDto.id(),
                segnalazioneDto.descrizione(),
                getGestoreComunale(idComune).getContenutoById(segnalazioneDto.idContenuto())
        );
    }



    private static GestoreComunale getGestoreComunale(String idComune) {

        return GestorePiattaforma.getInstance().getGestoreComuni().getGestoreComunale(
                GestorePiattaforma.getInstance().getGestoreComuni().getComuneById(idComune)
        );

    }



}