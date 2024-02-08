import { Component } from '@angular/core';
import { ServiziopuntiService } from '../services/serviziopunti.service';
import { PuntoFisicoMinimo } from '../interfaces/PuntoFisico';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-punti',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './punti.component.html',
  styleUrl: './punti.component.scss'
})
export class PuntiComponent {

  punti: PuntoFisicoMinimo[] = [];
  id!: number;

  constructor(private servizio: ServiziopuntiService, private router: ActivatedRoute) {
    this.router.params.subscribe(params => {
      this.id = params['id'];
      this.servizio.getPunti(params['id']).subscribe(
        risposta => {
          this.punti = risposta;
        });
    });
  }
}