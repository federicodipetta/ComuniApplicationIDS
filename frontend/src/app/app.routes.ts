import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PuntiComponent } from './punti/punti.component';
import { RichiesteComponent } from './richieste/richieste.component';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'punti/:id', component: PuntiComponent },//l'id è l'id del comune
    { path: 'richiesta/:id', component: RichiesteComponent },//l'id è l'id del comune
    { path: '**', component: HomeComponent }
];
