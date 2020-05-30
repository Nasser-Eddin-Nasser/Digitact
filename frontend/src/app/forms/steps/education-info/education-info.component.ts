/*
  @description
    This component renders the education information step view and its actions.
*/
import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-education-info',
  templateUrl: './education-info.component.html',
  styleUrls: ['./education-info.component.scss'],
})
export class EducationInfoComponent {
  constructor() {
    console.log('This is EducationInfoComponent');
  }
  @Input()
  formsData: FormGroup<FormsData>;
}
