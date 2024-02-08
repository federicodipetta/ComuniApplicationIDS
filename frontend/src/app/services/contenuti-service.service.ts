import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ContenutoAdd, ContenutoAddW, ContenutoDettagliato, ContenutoMinimo } from '../interfaces/Contenuto';

@Injectable({
  providedIn: 'root'
})
export class ContenutiServiceService {
  contenuti: ContenutoDettagliato[] = [];
  constructor(private httpClient: HttpClient) { }

  public getContenuto(idComune: number, idContenuto: number) {
    return this.httpClient.get<ContenutoDettagliato>('/api/v0/elementi/getContenuto?idComune=' + idComune + '&idContenuto=' + idContenuto);
  }

  public getContenuti(idComune: number) {
    return this.httpClient.get<ContenutoMinimo[]>('/api/v0/elementi/getContenuti?idComune=' + idComune);
  }

  public addContenuto(contenuto: ContenutoAddW, idComune: string) {
    return this.httpClient.post<ContenutoDettagliato>('/api/v0/elementi/aggiungiContenuto?id=' + idComune, contenuto);
  }
}
