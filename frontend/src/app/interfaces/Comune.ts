import { PuntoFisico } from "./PuntoFisico";

export interface Comune {
    id: string;
    nome: string;
    provincia: string;
    puntoComune: PuntoFisico;
}

export interface ComuneAdd {
    nome: string;
    provincia: string;
    id: string;
}