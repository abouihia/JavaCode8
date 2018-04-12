import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';


import { AppComponent } from './app.component';
import { AccueilComponentComponent } from './accueil-component/accueil-component.component';
import { ActuComponentComponent } from './actu-component/actu-component.component';
import { ContactComponentComponent } from './contact-component/contact-component.component';

/*
  router concept in Angular
  1-first step
 */
import {Routes, RouterModule} from '@angular/router';

const routes: Routes = [

  { path: 'Accueil', component: AccueilComponentComponent },
  { path: 'Actualite', component: ActuComponentComponent },
  { path: 'Contact', component: ContactComponentComponent },
  { path: '', redirectTo: '/Accueil', pathMatch: 'full'}

];




@NgModule({
  declarations: [AppComponent, AccueilComponentComponent, ActuComponentComponent, ContactComponentComponent  ],
  imports: [ BrowserModule,
                BsDropdownModule.forRoot(),
                TooltipModule.forRoot(),
                ModalModule.forRoot(),
                RouterModule.forRoot(routes )],//  seconde step router
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
