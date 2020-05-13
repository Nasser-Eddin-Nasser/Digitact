import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ApplicationFormPage } from './application-form.page';

const routes: Routes = [
  {
    path: '',
    component: ApplicationFormPage,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ApplicationFormPageRoutingModule {}
