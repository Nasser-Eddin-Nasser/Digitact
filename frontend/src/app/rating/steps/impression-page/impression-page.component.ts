/**
 * @description
 *  This component renders the impression of HR on Applicant
 */
import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { RatingForm } from '../../model/rating-form.model';

@Component({
  selector: 'app-impression-page',
  templateUrl: './impression-page.component.html',
  styleUrls: ['./impression-page.component.scss'],
})
export class ImpressionPageComponent {
  @Input()
  ratingForm: FormGroup<RatingForm>;
}
