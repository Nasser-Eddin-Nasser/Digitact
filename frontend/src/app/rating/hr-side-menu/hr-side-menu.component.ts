import { Component, Input } from '@angular/core';

import { FormGroup } from '../../common/forms/forms';
import { RatingForm } from '../model/rating-form.model';

@Component({
  selector: 'app-hr-side-menu',
  templateUrl: './hr-side-menu.component.html',
  styleUrls: ['./hr-side-menu.component.scss'],
})
export class HrSideMenuComponent {
  @Input()
  ratingForm: FormGroup<RatingForm>;
}
