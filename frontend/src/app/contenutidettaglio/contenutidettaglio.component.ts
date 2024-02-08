import { Component } from '@angular/core';
import { ContenutiServiceService } from '../services/contenuti-service.service';
import { ContenutoDettagliato } from '../interfaces/Contenuto';
import { ActivatedRoute } from '@angular/router';
import { RichiesteService } from '../services/richieste.service';

@Component({
  selector: 'app-contenutidettaglio',
  standalone: true,
  imports: [],
  templateUrl: './contenutidettaglio.component.html',
  styleUrl: './contenutidettaglio.component.scss'
})
export class ContenutidettaglioComponent {

  contenuto!: ContenutoDettagliato;
  idComune!: string;
  idContenuto!: string;
  latitudine!: number;
  longitudine!: number;

  constructor(private servizio: ContenutiServiceService, private servizioRichieste: RichiesteService, private router: ActivatedRoute) {
    this.router.params.subscribe(params => {
      this.idComune = params['idComune'];
      this.idContenuto = params['idContenuto'];
      this.latitudine = params['lat'];
      this.longitudine = params['lon'];
      this.servizio.getContenuto(this.idComune, this.idContenuto).subscribe(
        risposta => {
          this.contenuto = risposta;
        });
    });
  }

  eliminaContenuto() {
    this.servizioRichieste.richiestaEliminazione(this.idComune, this.idContenuto, this.latitudine, this.longitudine).subscribe();
  }
  
}
