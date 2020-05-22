/*
@Author
Bharathwaj Ravi

Add modifiers under @Modifier
@Modifiers

@Purpose
  - This page has the route path configuration in forms module.
*/
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FormsPage } from './forms.page';

const routes: Routes = [
  {
    path: '',
    component: FormsPage,
  },
  {
    path: 'confirmation',
    loadChildren: () =>
      import('./confirmation/confirmation.module').then(
        (m) => m.ConfirmationPageModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FormsPageRoutingModule {}
