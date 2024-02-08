package unicam.cs.ids.mappers;

import unicam.cs.ids.dtos.ContenutoDto;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.punti.ContenutoBuilder;
import unicam.cs.ids.models.stato.Stato;

public class ContenutoMapper {

    static public Contenuto mapContenuto(ContenutoDto contenutoDto){
        System.out.println(contenutoDto.getTempoAstratto());
        System.out.println(contenutoDto.getTempo());
        return new ContenutoBuilder().setId(contenutoDto.getId())
                .setTitolo(contenutoDto.getTitolo())
                .setTesto(contenutoDto.getTesto())
                .setIscritti(contenutoDto.getIscritti())
                .setTempo(contenutoDto.getTempoAstratto())
                .setStato(contenutoDto.getStato())
                .setContenuti(contenutoDto.getContenuti(),contenutoDto.ordinato())
                .build();
    }
    static public Contenuto mapContenutoRichiesta(ContenutoDto contenutoDto){
        return new ContenutoBuilder().setId(contenutoDto.getId())
                .setTitolo(contenutoDto.getTitolo())
                .setTesto(contenutoDto.getTesto())
                .setIscritti(contenutoDto.getIscritti())
                .setStato(Stato.DA_ACCETTARE)
                .setContenuti(contenutoDto.getContenuti(),contenutoDto.ordinato())
                .build();
    }

}
