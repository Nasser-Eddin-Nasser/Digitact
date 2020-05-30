/*
 * @description
 *   This page has the route path configuration for confirmation page.
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ConfirmationPage } from './confirmation.page';

const routes: Routes = [
  {
    path: '',
    component: ConfirmationPage,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ConfirmationPageRoutingModule {}
