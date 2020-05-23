import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RatingPage } from './rating.page';

const routes: Routes = [
  {
    path: '',
    component: RatingPage,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RatingPageRoutingModule {}
