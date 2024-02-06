import { ContenutoDettagliato } from "./Contenuto"
import { PuntoFisicoMinimo } from "./PuntoFisico"

export interface Richiesta {
    id: string
}

export interface RichiestaSegnalazione extends Richiesta {
    testo: string
}

export interface RichiestaAggiuntaContenuto extends Richiesta {
    contenuto: ContenutoDettagliato,
    puntoFisico: PuntoFisicoMinimo
}

export interface RichiestaEliminazioneContenuto extends Richiesta {
    contenuto: ContenutoDettagliato,
    puntoFisico: PuntoFisicoMinimo
}

export interface RichiestaFile extends Richiesta {
    idContenuto: string,
    file: File[]
}

export interface Valutazione {
    id: string,
    risposta: boolean
}