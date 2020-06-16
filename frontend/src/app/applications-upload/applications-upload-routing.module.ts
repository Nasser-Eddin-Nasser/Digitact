/*
 * @description
 *  This page has the route path configuration for applications upload page.
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ApplicationsUploadPage } from './applications-upload.page';

const routes: Routes = [
  {
    path: '',
    component: ApplicationsUploadPage,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ApplicationsUploadPageRoutingModule {}
