/**
 * @description
 *   This page has the route path configuration in privacy  module.
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PrivacyPage } from './privacy.page';

const routes: Routes = [
  {
    path: '',
    component: PrivacyPage,
  },
  {
    path: 'details',
    loadChildren: () =>
      import('./details/details.module').then((m) => m.DetailsPageModule),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PrivacyPageRoutingModule {}
