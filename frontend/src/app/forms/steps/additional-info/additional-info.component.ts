/**
 * @description
 *  This component renders the additional information given by Applicant
 */
import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';
@Component({
  selector: 'form-additional-info',
  templateUrl: './additional-info.component.html',
  styleUrls: ['./additional-info.component.scss'],
})
export class AdditionalInfoComponent {
  @Input()
  formsData: FormGroup<FormsData>;
}
