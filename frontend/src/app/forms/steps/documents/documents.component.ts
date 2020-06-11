/*
  @description
    This component renders the documents step view and its actions.
*/
import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.scss'],
})
export class DocumentsComponent {
  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;
}
