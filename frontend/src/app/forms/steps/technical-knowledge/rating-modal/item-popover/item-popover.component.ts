import { Component, Input } from '@angular/core';

import { FormGroup } from '../../../../../common/forms/forms';
import { TechnicalKnowledgeEntry } from '../../../../../model/forms-data.model';

@Component({
  templateUrl: './item-popover.component.html',
  styleUrls: ['./item-popover.component.scss'],
})
export class ItemPopoverComponent implements ItemPopoverProps {
  @Input()
  formItem: FormGroup<TechnicalKnowledgeEntry>;
}

export interface ItemPopoverProps {
  formItem: FormGroup<TechnicalKnowledgeEntry>;
}
