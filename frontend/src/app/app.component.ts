import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ComuniService } from './services/comuni.service';
import { Comune } from './interfaces/Comune';
import { TendinaService } from './services/tendina.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, FormsModule, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
  comuni: Comune[] = [];
  subscription: Subscription;
  idc: string = "0";

  constructor(private router: Router, private comuniService: ComuniService, private tendina: TendinaService) {
    this.subscription = this.tendina.menuData$.subscribe((comuni) => {
      console.log(comuni);
      this.comuni = comuni;
      this.idc = this.comuni[0].id;
      console.log(this.comuni);
    });

    this.comuniService.getComuni().subscribe((comuni) => {
      this.comuni = comuni;
      this.idc = this.comuni[0].id;
      // console.log(this.comuni);
      console.log(this.idc);
    });

  }

  onChange() {
    this.router.navigate(['/']);
  }

}
