import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { RatingPageRoutingModule } from './rating-routing.module';
import { RatingPage } from './rating.page';
import { ApplicantScoreComponent } from './steps/applicant-score/applicant-score.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    IonicModule,
    RatingPageRoutingModule,
  ],
  declarations: [RatingPage, ApplicantScoreComponent],
})
export class RatingPageModule {}
