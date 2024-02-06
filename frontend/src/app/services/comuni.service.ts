import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comune, ComuneAdd } from '../interfaces/Comune';

@Injectable({
  providedIn: 'root'
})
export class ComuniService {

  constructor(private http: HttpClient) { }

  getComuni(): Observable<Comune[]> {
    return this.http.get<Comune[]>('/api/v0/comuni/');
  }

  addComune(comune: ComuneAdd) {
    return this.http.post('/api/v0/comuni/add', comune)
  }
}
