import { PuntoFisicoMinimo } from "./PuntoFisico";

export interface Comune {
    id: string;
    nome: string;
    provincia: string;
    puntoComune: PuntoFisicoMinimo;
}

export interface ComuneAdd {
    nome: string;
    provincia: string;
    id: string;
}