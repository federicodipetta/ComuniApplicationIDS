import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { OrariFormComponent } from '../orari-form/orari-form.component';
import { Fascia, FasciaAdd } from '../interfaces/Tempo';
import { ComuniService } from '../services/comuni.service';
import { Comune } from '../interfaces/Comune';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';
import { ServiziopuntiService } from '../services/serviziopunti.service';
import { PuntoFisicoDettagliato, PuntoFisicoMinimo } from '../interfaces/PuntoFisico';
import { ContenutoAdd, ContenutoAddW } from '../interfaces/Contenuto';
import { ContenutiServiceService } from '../services/contenuti-service.service';

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

  constructor(private servizio: ComuniService, private puntis: ServiziopuntiService
    , private contentservice: ContenutiServiceService) {
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
    punto: new FormControl('0'),
    latitudine: new FormControl('0'),
    longitudine: new FormControl('0'),
    tipo: new FormControl('0'),
    coordinate: new FormControl('0')
  });

  submitForm() {
    let value = this.formGroup.value;
    let contenuti = [];
    let fasciaAdd: FasciaAdd[] = [];
    this.orari.forEach(x => {
      fasciaAdd.push({ inizio: x.inizio.toString(), fine: x.fine.toString() });
    });
    console.log(fasciaAdd);
    console.log(value)
    let contenutoAdd: ContenutoAddW = {
      contenuto: {
        titolo: value.titolo,
        testo: value.descrizione,
        tempo: value.tipo == '0' ? [] : fasciaAdd,
        contenuti: value.tipo == '2' ? [] : [],
        stato: 0
      },
      puntoFisico: {
        coordinate: {
          latitudine: value.punto == '1' ? value.latitudine : this.punti[parseInt(value.coordinate)].coordinate.latitudine,
          longitudine: value.punto == '1' ? value.longitudine : this.punti[parseInt(value.coordinate)].coordinate.longitudine
        }
      }
    };
    console.log(contenutoAdd);
    this.contentservice.addContenuto(contenutoAdd, this.idc).subscribe(x => {

    });

  }

  handleOrarioInserito(orario: Fascia) {
    this.orari.push(orario);
    console.log(this.orari);
  }

}
