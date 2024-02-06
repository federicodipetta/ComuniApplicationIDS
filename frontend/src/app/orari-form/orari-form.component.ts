import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Fascia } from '../interfaces/Tempo';

@Component({
  selector: 'app-orari-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './orari-form.component.html',
  styleUrl: './orari-form.component.scss'
})
export class OrariFormComponent {
  formGroup: FormGroup = new FormGroup({
    orarioInizio: new FormControl(''),
    orarioFine: new FormControl('')
  });
  @Output() orarioInserito = new EventEmitter<Fascia>();
  submitForm() {
    let fascia: Fascia = {
      inizio: this.formGroup.value.orarioInizio,
      fine: this.formGroup.value.orarioFine
    };

    this.orarioInserito.emit(fascia);
  }
}
