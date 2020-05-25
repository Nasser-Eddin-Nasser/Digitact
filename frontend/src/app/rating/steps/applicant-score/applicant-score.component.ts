import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { RatingForm } from '../../model/rating-form.model';

@Component({
  selector: 'app-applicant-score',
  templateUrl: './applicant-score.component.html',
  styleUrls: ['./applicant-score.component.scss'],
})
export class ApplicantScoreComponent {
  @Input()
  ratingForm: FormGroup<RatingForm>;
}
