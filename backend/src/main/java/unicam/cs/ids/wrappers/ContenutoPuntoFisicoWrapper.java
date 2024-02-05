package unicam.cs.ids.wrappers;

import unicam.cs.ids.dtos.ContenutoDto;
import unicam.cs.ids.models.punti.PuntoFisico;

/**
 * Classe che rappresenta un contenuto e un punto fisico
 * @param contenuto Contenuto DTO.
 * @param puntoFisico Punto fisico.
 */
public record ContenutoPuntoFisicoWrapper(ContenutoDto contenuto, PuntoFisico puntoFisico) {

}
