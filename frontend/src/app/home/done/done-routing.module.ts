import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DonePage } from './done.page';

const routes: Routes = [
  {
    path: '',
    component: DonePage,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DonePageRoutingModule {}
