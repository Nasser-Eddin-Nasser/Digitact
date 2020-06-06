/**
 * @description
 *  This module serves as a common place where the rating related pages, componets are handled.
 */

import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { HrSideMenuComponent } from './hr-side-menu/hr-side-menu.component';
import { RatingPageRoutingModule } from './rating-routing.module';
import { RatingPage } from './rating.page';
import { ApplicantScoreComponent } from './steps/applicant-score/applicant-score.component';
import { FinalizePageComponent } from './steps/finalize-page/finalize-page.component';
import { ImpressionPageComponent } from './steps/impression-page/impression-page.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonicModule,
    RatingPageRoutingModule,
  ],
  declarations: [
    RatingPage,
    ApplicantScoreComponent,
    ImpressionPageComponent,
    HrSideMenuComponent,
    FinalizePageComponent,
  ],
})
export class RatingPageModule {}
