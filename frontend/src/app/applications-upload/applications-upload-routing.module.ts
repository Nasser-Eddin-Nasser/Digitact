import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ApplicationsUploadPage } from './applications-upload.page';

const routes: Routes = [
  {
    path: '',
    component: ApplicationsUploadPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ApplicationsUploadPageRoutingModule {}
