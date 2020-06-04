import { Component, Input } from '@angular/core';
import { PopoverController } from '@ionic/angular';

import { FormGroup } from '../../../../../common/forms/forms';
import { TechnicalKnowledgeEntry } from '../../../../../model/forms-data.model';
import { TechnicalKnowledgeFormItemsService } from '../../technical-knowledge-form-items.service';

@Component({
  templateUrl: './item-popover.component.html',
  styleUrls: ['./item-popover.component.scss'],
})
export class ItemPopoverComponent implements ItemPopoverProps {
  @Input()
  formItem: FormGroup<TechnicalKnowledgeEntry>;

  constructor(
    private popoverController: PopoverController,
    private techicalKnowledgeFormItemsService: TechnicalKnowledgeFormItemsService
  ) {}

  closePopover(): void {
    this.popoverController.dismiss();
  }

  deleteItem(): void {
    this.techicalKnowledgeFormItemsService.resetFormItem(this.formItem);
    this.closePopover();
  }
}

export interface ItemPopoverProps {
  formItem: FormGroup<TechnicalKnowledgeEntry>;
}
