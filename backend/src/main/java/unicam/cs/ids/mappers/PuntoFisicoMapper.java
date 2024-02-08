package unicam.cs.ids.mappers;

import unicam.cs.ids.dtos.PuntoFisicoDto;
import unicam.cs.ids.models.punti.Coordinate;
import unicam.cs.ids.models.punti.PuntoFisico;

import java.util.ArrayList;
import java.util.HashSet;

public class PuntoFisicoMapper {



    public static PuntoFisico mapPuntoFisico(PuntoFisicoDto puntoFisicoDto){
        return new PuntoFisico(new Coordinate(puntoFisicoDto.latitudine(), puntoFisicoDto.longitudine()) ,
                new HashSet<>());
    }
}
