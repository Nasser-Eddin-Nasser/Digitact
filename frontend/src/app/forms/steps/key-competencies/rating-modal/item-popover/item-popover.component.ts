/*
  @description
    This Component displays the content of the popover that is shown when clicking on an item in the list shown in the rating modal.
*/
import { Component, Input } from '@angular/core';
import { PopoverController } from '@ionic/angular';

import { FormGroup } from '../../../../../common/forms/forms';
import { KeyCompetenciesEntry } from '../../../../../model/forms-data.model';
import { KeyCompetenciesFormItemsService } from '../../key-competencies-form-items.service';

@Component({
  templateUrl: './item-popover.component.html',
  styleUrls: ['./item-popover.component.scss'],
})
export class ItemPopoverComponent implements ItemPopoverProps {
  @Input()
  formItem: FormGroup<KeyCompetenciesEntry>;

  constructor(
    private popoverController: PopoverController,
    private keyCompetenciesFormItemsService: KeyCompetenciesFormItemsService
  ) {}

  closePopover(): void {
    this.popoverController.dismiss();
  }

  deleteItem(): void {
    this.keyCompetenciesFormItemsService.resetFormItem(this.formItem);
    this.closePopover();
  }
}

export interface ItemPopoverProps {
  formItem: FormGroup<KeyCompetenciesEntry>;
}
