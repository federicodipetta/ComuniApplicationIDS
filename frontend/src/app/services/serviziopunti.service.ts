import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PuntoFisicoMinimo } from '../interfaces/PuntoFisico';
import { ContenutoMinimo } from '../interfaces/Contenuto';

@Injectable({
  providedIn: 'root'
})
export class ServiziopuntiService {

  constructor(private httpClient: HttpClient) { }

  public getPunti(number: string) {
    return this.httpClient.get<PuntoFisicoMinimo[]>('/api/v0/elementi/getPunti?id=' + number);
  }

  public getContenuti(idComune: number, lat: number, lon: number) {
    return this.httpClient.get<ContenutoMinimo[]>('/api/v0/elementi/getContenuti?id=' + idComune + '&lat=' + lat + '&lon=' + lon);
  }

}
