import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-submit-page',
  templateUrl: './submit-page.component.html',
  styleUrls: ['./submit-page.component.scss'],
})
export class SubmitPageComponent {
  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;
}
