import { ContenutoDettagliato, ContenutoMinimo } from "./Contenuto";
import { Coordinate } from "./Coordinate";

export interface PuntoFisicoMinimo {
    coordinate: Coordinate;
    numeroContenuti: number;
}

export interface PuntoFisicoDettagliato {
    coordinate: Coordinate;
    contenuti: ContenutoMinimo[];
}