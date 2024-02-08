import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComuniService } from '../services/comuni.service';
import { TendinaService } from '../services/tendina.service';

@Component({
  selector: 'app-add-comune',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './add-comune.component.html',
  styleUrl: './add-comune.component.scss'
})
export class AddComuneComponent {

  formgroup: FormGroup = new FormGroup(
    {
      nome: new FormControl(''),
      provincia: new FormControl(''),
    }
  )
  constructor(private comuniService: ComuniService, private tendina: TendinaService) {

  }


  addComune() {
    console.log(this.formgroup.value);
    this.comuniService.addComune(this.formgroup.value).subscribe(
      data => {
        console.log(data);
        this.tendina.fetchMenuData();
      },
      error => {
        this.tendina.fetchMenuData();
      }
    );

  }
}
