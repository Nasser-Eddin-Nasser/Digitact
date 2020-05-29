/*
  @description
    This component renders the basic information step view and its actions.
*/
import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss'],
})
export class BasicInfoComponent {
  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;

  /**
   * The possible salutations, displayed in the dropdown menu.
   */
  salutationsArray = [
    { value: 'mr', displayName: 'Mr' },
    { value: 'mrs', displayName: 'Mrs' },
  ];
}
