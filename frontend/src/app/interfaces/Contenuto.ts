import { PuntoFisicoAdd, PuntoFisicoMinimo } from "./PuntoFisico";
import { Stato } from "./Stato";
import { Fascia, FasciaAdd, Tempo } from "./Tempo";

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

export interface ContenutoAdd {
    titolo: string;
    testo: string;
    tempo: FasciaAdd[];
    contenuti: string[];
    stato: Stato;
}
export interface ContenutoAddW {

    contenuto: ContenutoAdd;
    puntoFisico: PuntoFisicoAdd;
}