import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PuntiComponent } from './punti/punti.component';
import { RichiesteComponent } from './richieste/richieste.component';
import { ContenutiComponent } from './contenuti/contenuti.component';
import { ContenutidettaglioComponent } from './contenutidettaglio/contenutidettaglio.component';
import { AddComuneComponent } from './add-comune/add-comune.component';
import { AddContenutiComponent } from './add-contenuti/add-contenuti.component';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'add-comune', component: AddComuneComponent },
    { path: 'add-contenuto', component: AddContenutiComponent },
    { path: 'punti/:idComune/:lat/:lon/:idContenuto', component: ContenutidettaglioComponent },
    { path: 'punti/:id/:lat/:lon', component: ContenutiComponent },
    { path: 'punti/:id', component: PuntiComponent },//l'id è l'id del comune
    { path: 'richiesta/:id', component: RichiesteComponent },//l'id è l'id del comune
    { path: '**', component: HomeComponent }
];
