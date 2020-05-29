import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-field-designation-page',
  templateUrl: './field-designation-page.component.html',
  styleUrls: ['./field-designation-page.component.scss'],
})
export class FieldDesignationPageComponent {
  @Input()
  formsData: FormGroup<FormsData>;
}
