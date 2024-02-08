import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OrariFormComponent } from '../orari-form/orari-form.component';
import { Fascia } from '../interfaces/Tempo';
import { ComuniService } from '../services/comuni.service';
import { Comune } from '../interfaces/Comune';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { ServiziopuntiService } from '../services/serviziopunti.service';
import { PuntoFisicoDettagliato, PuntoFisicoMinimo } from '../interfaces/PuntoFisico';

@Component({
  selector: 'app-add-contenuti',
  standalone: true,
  imports: [CommonModule, RouterOutlet, FormsModule, RouterLink, ReactiveFormsModule, OrariFormComponent],
  templateUrl: './add-contenuti.component.html',
  styleUrl: './add-contenuti.component.scss'
})
export class AddContenutiComponent {
  orari: Fascia[] = [];
  tipo: string = '0';// 0 = POI, 1 = Evento, 2 = Itinerario
  idc: string = '0';
  comuni: Comune[] = []
  punti: PuntoFisicoMinimo[] = []

  constructor(private servizio: ComuniService, private puntis: ServiziopuntiService) {
    servizio.getComuni().subscribe(x => {
      this.comuni = x
      this.idc = this.comuni[0].id
      puntis.getPunti(this.idc).subscribe(x => {
        this.punti = x;
      });
    });

  }

  formGroup: FormGroup = new FormGroup({
    id: new FormControl(''),
    titolo: new FormControl(''),
    descrizione: new FormControl(''),
    punto: new FormControl(''),
    latitudine: new FormControl(''),
    longitudine: new FormControl(''),
    tipo: new FormControl('0')
  });

  submitForm() {
    console.log(this.formGroup.value);
  }

  handleOrarioInserito(orario: Fascia) {
    this.orari.push(orario);
    console.log(this.orari);
  }

}
