import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OrariFormComponent } from '../orari-form/orari-form.component';
import { Fascia } from '../interfaces/Tempo';
import { ComuniService } from '../services/comuni.service';
import { Comune } from '../interfaces/Comune';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-add-contenuti',
  standalone: true,
  imports: [CommonModule, RouterOutlet, FormsModule, RouterLink, ReactiveFormsModule, OrariFormComponent],
  templateUrl: './add-contenuti.component.html',
  styleUrl: './add-contenuti.component.scss'
})
export class AddContenutiComponent {
  orari: Set<Fascia> = new Set();
  tipo: string = '0';// 0 = POI, 1 = Evento, 2 = Itinerario
  idc: string = '0';
  comuni: Comune[] = []

  constructor(private servizio: ComuniService) {
    servizio.getComuni().subscribe(x => {
      this.comuni = x
      this.idc = this.comuni[0].id
    });
  }

  formGroup: FormGroup = new FormGroup({
    id: new FormControl(''),
    titolo: new FormControl(''),
    descrizione: new FormControl(''),
    punto: new FormControl('')
  });

  submitForm() {
    console.log(this.formGroup.value);
  }

  handleOrarioInserito(orario: Fascia) {
    this.orari.add(orario);
    console.log(this.orari);
  }

}
