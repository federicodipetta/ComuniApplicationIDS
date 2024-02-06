import { Component } from '@angular/core';
import { ContenutiServiceService } from '../services/contenuti-service.service';
import { ContenutoDettagliato } from '../interfaces/Contenuto';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-contenutidettaglio',
  standalone: true,
  imports: [],
  templateUrl: './contenutidettaglio.component.html',
  styleUrl: './contenutidettaglio.component.scss'
})
export class ContenutidettaglioComponent {

  contenuto!: ContenutoDettagliato;
  idComune!: number;
  idContenuto!: number;

  constructor(private servizio: ContenutiServiceService, private router: ActivatedRoute) {
    this.router.params.subscribe(params => {
      this.idComune = params['idComune'];
      this.idContenuto = params['idContenuto'];
      this.servizio.getContenuto(this.idComune, this.idContenuto).subscribe(
        risposta => {
          this.contenuto = risposta;
        });
    });
  }
  
}
