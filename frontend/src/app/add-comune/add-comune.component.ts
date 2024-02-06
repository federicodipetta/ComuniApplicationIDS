import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComuniService } from '../services/comuni.service';

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
      id: new FormControl(''),
    }
  )
  constructor(private comuniService: ComuniService) {

  }


  addComune() {
    console.log(this.formgroup.value);
    this.comuniService.addComune(this.formgroup.value).subscribe();
  }
}
