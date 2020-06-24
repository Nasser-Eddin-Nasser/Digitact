/*
 * @description
 *   This module serves as a common place where appplication page components are handled.
 */
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from '../shared.module';

import { ApplicantsPageRoutingModule } from './applicants-routing.module';
import { ApplicantsPage } from './applicants.page';

@NgModule({
  imports: [
    CommonModule,
    IonicModule,
    ApplicantsPageRoutingModule,
    SharedModule,
  ],
  declarations: [ApplicantsPage],
})
export class ApplicantsPageModule {}
