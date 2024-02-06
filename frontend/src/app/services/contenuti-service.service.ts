import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ContenutoDettagliato } from '../interfaces/Contenuto';

@Injectable({
  providedIn: 'root'
})
export class ContenutiServiceService {
  contenuti: ContenutoDettagliato[] = [];
  constructor(private httpClient: HttpClient) {}

  public getContenuto(idComune: number, idContenuto: number) {
    return this.httpClient.get<ContenutoDettagliato>('/api/v0/elementi/getContenuto?idComune='+ idComune + '&idContenuto=' + idContenuto);
  }
}
