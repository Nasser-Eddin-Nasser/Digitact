import { Component } from '@angular/core';

import { FormControl, FormGroup } from '../common/forms/forms';

import { ApplicantScore, RatingForm } from './model/rating-form.model';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.page.html',
  styleUrls: ['./rating.page.scss'],
})
export class RatingPage {
  ratingForm = new FormGroup<RatingForm>({
    applicantScore: new FormGroup<ApplicantScore>({
      rhetoric: new FormControl(),
      motivation: new FormControl(),
      selfAssurance: new FormControl(),
      personalImpression: new FormControl(),
    }),
  });
}
