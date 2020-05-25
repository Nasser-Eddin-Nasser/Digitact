import { Component } from '@angular/core';
import { Validators } from '@angular/forms';
import { NavController } from '@ionic/angular';

import { FormControl, FormGroup } from '../common/forms/forms';

import { ApplicantScore, RatingForm } from './model/rating-form.model';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.page.html',
  styleUrls: ['./rating.page.scss'],
})
export class RatingPage {
  constructor(private navController: NavController) {}

  ratingForm = new FormGroup<RatingForm>({
    applicantScore: new FormGroup<ApplicantScore>({
      rhetoric: new FormControl(undefined, Validators.required),
      motivation: new FormControl(undefined, Validators.required),
      selfAssurance: new FormControl(undefined, Validators.required),
      personalImpression: new FormControl(undefined, Validators.required),
    }),
  });

  goToHomePage(): void {
    this.navController.navigateBack('/home');
  }
}
