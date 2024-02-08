import { Component } from '@angular/core';
import { ServiziopuntiService } from '../services/serviziopunti.service';
import { ContenutoMinimo } from '../interfaces/Contenuto';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-contenuti',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './contenuti.component.html',
  styleUrl: './contenuti.component.scss'
})
export class ContenutiComponent {

  contenuti: ContenutoMinimo[] = [];

  constructor(private servizio: ServiziopuntiService, private router: ActivatedRoute) {
    this.router.params.subscribe(params => {
      this.servizio.getContenuti(params['id'], params['lat'], params['lon']).subscribe(
        risposta => {
          this.contenuti = risposta;
        });
    });
  }

}
