import { Injectable } from '@angular/core';
import { ComuniService } from './comuni.service';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TendinaService {
  private menuDataSubject = new BehaviorSubject<any[]>([]);
  public menuData$: Observable<any[]> = this.menuDataSubject.asObservable();

  constructor(private http: ComuniService) { }

  fetchMenuData() {
    this.http.getComuni().subscribe(
      data => {
        this.menuDataSubject.next(data);
      },
      error => {
        console.error('Errore durante il recupero dei dati del menu:', error);
      }
    );
  }
}
