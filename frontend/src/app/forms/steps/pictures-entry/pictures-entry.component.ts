import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-pictures-entry',
  templateUrl: './pictures-entry.component.html',
  styleUrls: ['./pictures-entry.component.scss'],
})
export class PicturesEntryComponent {
  @Input()
  formsData: FormGroup<FormsData>;
}
