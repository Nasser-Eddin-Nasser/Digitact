/*
  @description
    This component renders the technical knowledge step view and its actions.
*/
import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../common/forms/forms';
import { FormsData } from '../../../model/forms-data.model';

@Component({
  selector: 'form-technical-knowledge',
  templateUrl: './technical-knowledge.component.html',
  styleUrls: ['./technical-knowledge.component.scss'],
})
export class TechnicalKnowledgeComponent {
  /**
   * Data of the entire form.
   */
  @Input()
  formsData: FormGroup<FormsData>;
}
