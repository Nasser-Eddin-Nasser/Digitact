/*
  @description
    This component renders the contact information step view and its actions.
*/
import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-contact-info',
  templateUrl: './contact-info.component.html',
  styleUrls: ['./contact-info.component.scss'],
})
export class ContactInfoComponent {
  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;
}
