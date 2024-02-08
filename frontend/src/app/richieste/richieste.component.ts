import { Component } from '@angular/core';
import { RichiesteService } from '../services/richieste.service';
import { Richiesta } from '../interfaces/Richiesta';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-richieste',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './richieste.component.html',
  styleUrl: './richieste.component.scss'
})

export class RichiesteComponent {

  richieste: Richiesta[] = [];
  id: string = "0";

  constructor(private service: RichiesteService, activatedRoute: ActivatedRoute) {
    activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      this.service.getRichieste(params['id']).subscribe(
        risposta => {
          this.richieste = risposta;
          console.log(this.richieste);
        });
    });
  } 
}
