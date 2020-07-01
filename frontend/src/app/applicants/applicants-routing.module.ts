/*
 * @description
 *   This page has the route path configuration for applications page.
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ApplicantsPage } from './applicants.page';

const routes: Routes = [
  {
    path: '',
    component: ApplicantsPage,
  },
  {
    path: 'applications-upload',
    loadChildren: () =>
      import('./applications-upload/applications-upload.module').then(
        (m) => m.ApplicationsUploadPageModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ApplicantsPageRoutingModule {}
