import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UploadComponent} from './upload/upload.component';
import {ImagesComponent} from './images/images.component';
import {EditComponent} from './edit/edit.component';
import {CanvasComponent} from './canvas/canvas.component';
import {DomainComponent} from './domain/domain.component';

const routes: Routes = [
  { path: 'upload', component: UploadComponent},
  { path: 'dashboard', component: ImagesComponent},
  { path: 'edit/:id', component: EditComponent},
  { path: 'createCanvas', component: CanvasComponent},
  { path: 'explore', component: DomainComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
