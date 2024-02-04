import { Contenuto } from "./Contenuto";
import { Coordinate } from "./Coordinate";

export interface PuntoFisico {
    coordinate: Coordinate;
}

export interface PuntoFisicoDettagli {
    coordinate: Coordinate;
    contenuti: Contenuto[];
}