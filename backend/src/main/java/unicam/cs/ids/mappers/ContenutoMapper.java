package unicam.cs.ids.mappers;

import unicam.cs.ids.dtos.ContenutoDto;
import unicam.cs.ids.models.punti.Contenuto;
import unicam.cs.ids.models.punti.ContenutoBuilder;

public class ContenutoMapper {



    static public Contenuto mapContenuto(ContenutoDto contenutoDto){
        return new ContenutoBuilder().setId(contenutoDto.getId())
                .setTitolo(contenutoDto.getTitolo())
                .setTesto(contenutoDto.getTesto())
                .setIscritti(contenutoDto.getIscritti())
                .setStato(contenutoDto.getStato())
                .setContenuti(contenutoDto.getContenuti(),contenutoDto.ordianto())
                .build();
    }
}
