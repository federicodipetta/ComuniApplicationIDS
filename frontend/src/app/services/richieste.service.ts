import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Richiesta, RichiestaAggiuntaContenuto, Valutazione } from '../interfaces/Richiesta';

@Injectable({
  providedIn: 'root'
})
export class RichiesteService {

  constructor(private httpClient: HttpClient) { }

  public getRichieste(idComune: string) {
    return this.httpClient.get<Richiesta[]>('/api/v0/richieste?id='+idComune);
  }

  public getRichiesta(idRichiesta: string, idComune: string) {
    return this.httpClient.get<RichiestaAggiuntaContenuto>('/api/v0/richieste/getRichiesta?id='+idComune+'&idR='+idRichiesta);
  }

  public valutaRichiesta(idComune: string, valutazione: Valutazione) {
    return this.httpClient.post<Richiesta>('/api/v0/richieste/valuta?id='+idComune,valutazione);
  }

  public richiestaEliminazione(idComune: string, idContenuto: string, lat: number, lon: number) {
    const body = {
      idContenuto: idContenuto,
      puntoFisico: {
        latitudine: lat,
        longitudine: lon
      }
    }
    console.log(idComune);
    return this.httpClient.post<any>('/api/v0/richieste/contenuti/eliminazione?id='+idComune, body);
  }

}
