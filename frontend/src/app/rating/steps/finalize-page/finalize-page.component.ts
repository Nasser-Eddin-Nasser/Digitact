/**
 * @description
 *  This component is to finalize the HR rating
 */
import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { RatingForm } from '../../model/rating-form.model';

@Component({
  selector: 'app-finalize-page',
  templateUrl: './finalize-page.component.html',
  styleUrls: ['./finalize-page.component.scss'],
})
export class FinalizePageComponent {
  @Input()
  ratingForm: FormGroup<RatingForm>;
}
