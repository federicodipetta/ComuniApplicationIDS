import { Component } from '@angular/core';
import { Richiesta, RichiestaAggiuntaContenuto, Valutazione } from '../interfaces/Richiesta';
import { RichiesteService } from '../services/richieste.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-richiesta',
  standalone: true,
  imports: [],
  templateUrl: './richiesta.component.html',
  styleUrl: './richiesta.component.scss'
})

export class RichiestaComponent {

  richiesta!: RichiestaAggiuntaContenuto;
  id: string = "0";

  constructor(private service: RichiesteService, activatedRoute: ActivatedRoute) {
    activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      this.service.getRichiesta(params['idRichiesta'], params['id']).subscribe(
        risposta => {
          this.richiesta = risposta;
          console.log(this.richiesta);
        });
    });
  }

  onClick(valutazione: boolean) {
    let valuta: Valutazione = {id: this.richiesta.id, risposta: valutazione};
    this.service.valutaRichiesta(this.id, valuta).subscribe();
  }
}
