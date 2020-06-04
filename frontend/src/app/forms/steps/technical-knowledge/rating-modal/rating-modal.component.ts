import { Component, Input } from '@angular/core';
import { ModalController, PopoverController } from '@ionic/angular';

import { FormArray, FormGroup } from '../../../../common/forms/forms';
import { TechnicalKnowledgeEntry } from '../../../../model/forms-data.model';

import {
  ItemPopoverComponent,
  ItemPopoverProps,
} from './item-popover/item-popover.component';

@Component({
  templateUrl: './rating-modal.component.html',
  styleUrls: ['./rating-modal.component.scss'],
})
export class RatingModalComponent implements RatingModalProps {
  @Input()
  formArray: FormArray<TechnicalKnowledgeEntry>;

  constructor(
    private modalController: ModalController,
    private popoverController: PopoverController
  ) {}

  /**
   * Close this modal.
   */
  closeModal(): void {
    this.modalController.dismiss();
  }

  async showItemPopover(
    formItem: FormGroup<TechnicalKnowledgeEntry>,
    event: Event
  ): Promise<void> {
    const popoverProps: ItemPopoverProps = {
      formItem,
    };

    const popover = await this.popoverController.create({
      component: ItemPopoverComponent,
      componentProps: popoverProps,
      event,
      cssClass: ['popover-width-350'],
    });

    await popover.present();

    // We need to wait until the Popover has been presented.
    // Otherwise, the Popover might get positioned poorly since calling "enable" changes our view.
    formItem.enable();
  }
}

export interface RatingModalProps {
  formArray: FormArray<TechnicalKnowledgeEntry>;
}
