import { Stato } from "./Stato";
import { Tempo } from "./Tempo";

export interface ContenutoDettagliato {
    id: string;
    titolo: string;
    testo: string;
    fileMultimediali: File[];
    tempo: Tempo;
    stato: Stato;
}

export interface ContenutoMinimo {
    id: string;
    titolo: string;
    stato: Stato;
}