import { Component } from '@angular/core';
import { ComuniService } from '../services/comuni.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  title = 'frontend';
  constructor(private servizio: ComuniService) { }
  public prova() {
    this.servizio.getComuni().subscribe(
      (data) => {
        console.log(data);
      }
    );
  }
}
